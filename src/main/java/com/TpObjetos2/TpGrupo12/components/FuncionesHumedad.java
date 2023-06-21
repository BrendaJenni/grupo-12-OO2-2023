package com.TpObjetos2.TpGrupo12.components;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TpObjetos2.TpGrupo12.entities.MedicionHumedad;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.entities.Evento;

@Component
public class FuncionesHumedad {
	@Autowired
	@Qualifier("sensorHumedadService")
	private ISensorHumedadService sensorHumedadService;
		
	@Scheduled(fixedDelay=5000)
	public void medicionesHumedad() {
		MedicionHumedad implementar = sensorHumedadService.traerUltimaMedicion();
				
		LocalTime horaInicio = LocalTime.of(6, 0);
		LocalTime horaFin = LocalTime.of(22, 0);
		Random random = new Random();
		
		if(implementar == null) {
			//Se detiene el component
		}else {
			LocalTime horaMedAhora = implementar.getFechaRegistro().toLocalTime();
			// registro el horario en que funciona el riego
			if((horaMedAhora.isAfter(horaInicio) && horaMedAhora.isBefore(horaFin))) {
						
				if(implementar.getHumedad() < 60 && !implementar.isEstadoCesped()) { //si no esta mojado
							
					SensorHumedad sensorHumedad = (SensorHumedad)implementar.getDispositivo();
					sensorHumedad.setEncendido(true);
					implementar.setDispositivo(sensorHumedad);
					Evento agregar = new Evento("Regar cesped", implementar.getFechaRegistro(),implementar.getDispositivo());
					sensorHumedadService.insertOrUpdate((SensorHumedad)implementar.getDispositivo());
					sensorHumedadService.agregarEventos(implementar.getDispositivo(), agregar);
					implementar.setEstadoCesped(true);
						
				}else if(implementar.getHumedad() < 60 && implementar.isEstadoCesped()) { //si esta mojado
					SensorHumedad sensorHumedad = (SensorHumedad)implementar.getDispositivo();
					sensorHumedad.setEncendido(false);
					implementar.setDispositivo(sensorHumedad);
					Evento agregar = new Evento("Apagar riego", implementar.getFechaRegistro(),implementar.getDispositivo());
					sensorHumedadService.insertOrUpdate((SensorHumedad)implementar.getDispositivo());
					sensorHumedadService.agregarEventos(implementar.getDispositivo(), agregar);
					implementar.setEstadoCesped(false);
				}else { //si la humedad es mayo que 60 apaga el riego
					
					Evento agregar = new Evento("Apagar riego", implementar.getFechaRegistro(),implementar.getDispositivo());
					sensorHumedadService.agregarEventos(implementar.getDispositivo(), agregar);
					implementar.setEstadoCesped(true);
				}
					
			}else { //fuera de horario no riega
				Evento agregar = new Evento("Apagar riego", implementar.getFechaRegistro(),implementar.getDispositivo());
				sensorHumedadService.agregarEventos(implementar.getDispositivo(), agregar);
				implementar.setEstadoCesped(false);
			}
			List<SensorHumedad> dispositivos = sensorHumedadService.getAll();
			
			boolean check = this.medicionesCompletas(dispositivos);
			
			//llamo a la funcion para entender si las mediciones estan completas si es asi no agregamos nuevas
			if(check ==false) {
			LocalDateTime fechanueva = implementar.getFechaRegistro();
			fechanueva = fechanueva.plusHours(1);
			int humedadActual = random.nextInt(100) + 1;
			sensorHumedadService.agregarMedicion(implementar.getDispositivo(), fechanueva, humedadActual, implementar.isEstadoCesped());
		}
	}
}
	public boolean medicionesCompletas(List<SensorHumedad> dispo) {
		
		boolean devolver =  false;
		List<SensorHumedad> dispositivos = sensorHumedadService.getAll();
		
		//reviso todas las mediciones para ver si estan completas
	    int cantCompletos = 0;
        for (int i = 0 ; i < dispositivos.size() ; i++) {
        	if(dispositivos.get(i).getMediciones().size() == 10) {
        		cantCompletos = cantCompletos + 1;
        	}
        }
        //si las mediciones estan completas devuelvo true y no se van a generar mediciones nuevas
        if(cantCompletos == dispositivos.size()) {
        	devolver = true;
        }
        return devolver;
	}
}