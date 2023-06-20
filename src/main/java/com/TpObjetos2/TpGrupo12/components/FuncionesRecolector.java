package com.TpObjetos2.TpGrupo12.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TpObjetos2.TpGrupo12.TpGrupo12Application;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.services.IRecolectorInteligenteService;
import com.TpObjetos2.TpGrupo12.services.implementacion.DispositivoService;
import com.TpObjetos2.TpGrupo12.services.implementacion.RecolectorInteligenteService;

import jakarta.transaction.Transactional;
@Component
public class FuncionesRecolector {
	@Autowired
    @Qualifier("recolectorInteligenteService")
    private IRecolectorInteligenteService sensorRecolectorService;
	
	//Trae la ultima medicion 
	@Transactional
	public MedicionRecolector traerUnRecoAleatorio() {
		List<RecolectorInteligente> recolectores = sensorRecolectorService.getAll();
		int id = (int) (Math.random()*recolectores.size());
		RecolectorInteligente dispo = recolectores.get(id);
		List<Medicion> mediciones = dispo.getMediciones();
		if (mediciones.size() == 0) {
			MedicionRecolector m = new MedicionRecolector(recolectores.get(id), LocalDateTime.now(), false);
			sensorRecolectorService.agregarMedicion(dispo, LocalDateTime.now(), false);
			return m;
		}
		if(mediciones.size() == 10) {
    		//si entra aca ya no debemos agregar mediciones a este dispositivo
    		return null;
    	}else {
    		return (MedicionRecolector) mediciones.get(mediciones.size()-1);
    	}
	}
	
	@Scheduled(fixedDelay=5000)
	 public void chequearContenidoRecolector () {
		MedicionRecolector reco = traerUnRecoAleatorio();
		LocalTime ahora = LocalTime.now();
    	LocalDate fecha = LocalDate.now();
    	int boleano;
    	boolean estado;
    	//Se encarga de que este funcionando solo mientras la universidad esta abierta
    	if (ahora.isAfter(LocalTime.of(7, 0)) && ahora.isBefore(LocalTime.of(21, 0))){
    		//chequea si el tacho esta lleno y, de estarlo, crea un evento para vaciarlo
    		if (reco.isEstaLlenoAhora() == true) {
    			sensorRecolectorService.agregarEventos(reco.getDispositivo(),(new Evento ("El techo esta lleno, se envia una notificacion para vaciar", 
    					LocalDateTime.of(fecha, ahora), reco.getDispositivo())));
    			reco = vaciarRecolector(reco,ahora);
    			
    		}
    		//genera un booleano al azar
    		boleano = (int)(Math.random()*2);
    		if (boleano == 0) {
    			estado = true;
    		} else {
    			estado = false;
    		}
    		reco = cambiarEstadoRecolector(reco);
    		sensorRecolectorService.agregarMedicion(new MedicionRecolector(reco.getDispositivo(),LocalDateTime.of(fecha, ahora),estado));
    	}
    }
	
	//Cambia el estado de vacio a lleno o viceversa
    public MedicionRecolector cambiarEstadoRecolector (MedicionRecolector dispo) {
    	if (dispo.isEstaLlenoAhora() == true) {
    		dispo.setEstaLlenoAhora(false);
    	} else {
    		dispo.setEstaLlenoAhora(true);
    	}
    	return dispo;
    }
    
    //Se imita un operador ingresando manualmente que respondio al dispositivo pidiendo que se vacie
    public MedicionRecolector vaciarRecolector(MedicionRecolector reco, LocalTime hora) {
    	sensorRecolectorService.agregarEventos(reco.getDispositivo(),new Evento("Se vacio el tacho", LocalDateTime.of(reco.getFechaRegistro().toLocalDate(), 
				hora.plusMinutes((int)(Math.random()*(20-10+1)+10))), reco.getDispositivo()));
		return cambiarEstadoRecolector(reco);
	}

}
