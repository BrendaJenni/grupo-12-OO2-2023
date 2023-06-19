package com.TpObjetos2.TpGrupo12.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;

@Component
public class TaskSample {

	//RECOLECTOR INTELIGENTE
	@Scheduled(fixedDelay=30000)
	 public void chequearContenidoRecolector (MedicionRecolector reco) {
    	LocalTime ahora = reco.getFechaRegistro().toLocalTime();
    	LocalDate fecha = reco.getFechaRegistro().toLocalDate();
    	LocalTime chequeos = ahora;
    	int boleano;
    	boolean estado;
    	while (ahora.isAfter(LocalTime.of(7, 0)) && ahora.isBefore(LocalTime.of(9, 0))){
    		if (ahora.equals(chequeos)) {
    			if (reco.isEstaLlenoAhora() == true) {
    				reco.getDispositivo().getEventos().add(new Evento ("El techo esta lleno, se envia una notificacion para vaciar", LocalDateTime.of(fecha, 
    						ahora), reco.getDispositivo()));
    				reco = vaciarRecolector(reco,chequeos);
    				
    			}
    			boleano = (int)(Math.random()*2);
    			if (boleano == 0) {
    				estado = true;
    			} else {
    				estado = false;
    			}
    			reco = cambiarEstadoRecolector(reco);
    			reco.getDispositivo().getMediciones().add(new MedicionRecolector(reco.getDispositivo(),LocalDateTime.of(fecha, ahora),estado));
    			chequeos = chequeos.plusMinutes(30);
    		}
    	}
    }
	
	public MedicionRecolector cambiarEstadoRecolector (MedicionRecolector dispo, boolean nuevoEstado) {
    	dispo.setEstaLlenoAhora(nuevoEstado);
    	return dispo;
    }
    
    public MedicionRecolector cambiarEstadoRecolector (MedicionRecolector dispo) {
    	if (dispo.isEstaLlenoAhora() == true) {
    		dispo.setEstaLlenoAhora(false);
    	} else {
    		dispo.setEstaLlenoAhora(true);
    	}
    	return dispo;
    }
    
    public MedicionRecolector vaciarRecolector(MedicionRecolector reco, LocalTime hora) {
    	reco.getDispositivo().getEventos().add(new Evento("Se vacio el tacho", LocalDateTime.of(reco.getFechaRegistro().toLocalDate(), 
				hora.plusMinutes((int)(Math.random()*(20-10+1)+10))), reco.getDispositivo()));
		cambiarEstadoRecolector(reco);
		return reco;
	}

}
