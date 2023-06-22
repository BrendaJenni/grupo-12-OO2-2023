package com.TpObjetos2.TpGrupo12.components;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.MedicionEstacionamiento;

@Component
public class FuncionesEstacionamiento {
	@Autowired
	@Qualifier("estacionamientoService")
	private ISensorEstacionamientoService estacionamientoService;
		
	@Scheduled(fixedDelay=5000)
	public void medicionesEstacionamiento() {
		MedicionEstacionamiento implementar = estacionamientoService.traerUltimaMedicion();
		//LOS EVENTOS NO SE AGREGARÁN AUTOMÁTICAMENTE FUERA DE ESTE HORARIO: 6 am - 10 pm
		LocalTime horaInicio = LocalTime.of(6, 0);
		LocalTime horaFin = LocalTime.of(22, 0);
		Random random = new Random();
		

		if(implementar == null) {
		}else {
			LocalTime horaMedAhora = implementar.getFechaRegistro().toLocalTime();
			if(!(horaMedAhora.isAfter(horaFin) && horaMedAhora.isBefore(horaInicio))) {
						
				if(implementar.isEstadoLibre()) { 
					
					Evento agregar = new Evento("Plazas disponibles", implementar.getFechaRegistro(),implementar.getDispositivo());
					estacionamientoService.agregarEventos(implementar.getDispositivo(), agregar);
						
				}else if(!implementar.isEstadoLibre()) { //si esta mojado
					
					Evento agregar = new Evento("Estacionamiento lleno", implementar.getFechaRegistro(),implementar.getDispositivo());
					estacionamientoService.agregarEventos(implementar.getDispositivo(), agregar);
				}
					
					
			}else {
				
				Evento agregar = new Evento("Estacionamiento cerrado", implementar.getFechaRegistro(),implementar.getDispositivo());
				estacionamientoService.agregarEventos(implementar.getDispositivo(), agregar);
			}
			List<SensorEstacionamiento> dispositivos = estacionamientoService.getAll();
			
			boolean check = this.medicionesCompletas(dispositivos);
			
			if(check ==false) {
			LocalDateTime fechanueva = implementar.getFechaRegistro();
			fechanueva = fechanueva.plusHours(1);
			int estado = (int)(Math.random()*2);
			
			if (estado == 1) {
			    implementar.setEstadoLibre(true);
			 } else {
			    implementar.setEstadoLibre(false);
			 }
			estacionamientoService.agregarMedicion((SensorEstacionamiento) implementar.getDispositivo(), fechanueva, implementar.isEstadoLibre());
		}
	}
}
	public boolean medicionesCompletas(List<SensorEstacionamiento> dispo) {
		
		boolean devolver =  false;
		List<SensorEstacionamiento> dispositivos = estacionamientoService.getAll();
		
	    int cantCompletos = 0;
        for (int i = 0 ; i < dispositivos.size() ; i++) {
        	if(dispositivos.get(i).getMediciones().size() == 10) {
        		cantCompletos = cantCompletos + 1;
        	}
        }
        if(cantCompletos == dispositivos.size()) {
        	devolver = true;
        }
        return devolver;
	}
}
