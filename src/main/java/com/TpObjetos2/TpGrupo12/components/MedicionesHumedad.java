package com.TpObjetos2.TpGrupo12.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionHumedad;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;
import com.TpObjetos2.TpGrupo12.services.implementacion.EventoService;
import com.TpObjetos2.TpGrupo12.services.implementacion.SensorHumedadService;



//@Component
public class MedicionesHumedad {
	
	
	@Autowired
    @Qualifier("eventoService")
	private EventoService eventoService;
	@Autowired
    @Qualifier("sensorHumedadService")
	private SensorHumedadService sensorHumedadService;
	
	@Scheduled(fixedDelay=10000)
		public void verificarRiego () {
			
		
	    	LocalTime ahora = LocalTime.now();
	    	LocalDate fecha = LocalDate.now();
	    	double humedadActual = Math.random()*100-1;
	    	
	    	
	    	Evento evento;
	    	MedicionHumedad medicion = sensorHumedadService.traerAleatorio();
	    	boolean mojado = medicion.isEstadoCesped();
	    	
	    	SensorHumedad sensorHumedad = (SensorHumedad)medicion.getDispositivo();
	    	while(ahora.isAfter(LocalTime.of(6, 0)) && ahora.isBefore(LocalTime.of(20, 0))) {
	    		if(humedadActual < 20 && !mojado) {
	    			evento = new Evento("Regar el cesped",LocalDateTime.of(fecha,ahora),medicion.getDispositivo());
	    			eventoService.insertOrUpdate(evento);
	    			
	    			sensorHumedad.setEncendido(true);
	    			sensorHumedadService.insertOrUpdate(sensorHumedad);
	    			sensorHumedadService.agregarMedicion(medicion.getDispositivo(), LocalDateTime.of(fecha,ahora), humedadActual, mojado);
	    		}else if(mojado){
	    			evento = new Evento("Regar el cesped",LocalDateTime.of(fecha,ahora),medicion.getDispositivo());
	    			eventoService.insertOrUpdate(evento);
	    			sensorHumedad.setEncendido(false);
	    			sensorHumedadService.insertOrUpdate(sensorHumedad);
	    			sensorHumedadService.agregarMedicion(medicion.getDispositivo(), LocalDateTime.of(fecha,ahora), humedadActual, mojado);
	    		}
	    	}
	    	
		}
	
	
}
