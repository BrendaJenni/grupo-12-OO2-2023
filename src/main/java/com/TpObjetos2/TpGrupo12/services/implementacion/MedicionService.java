package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionModel;
import com.TpObjetos2.TpGrupo12.repositories.IEventoRepository;
import com.TpObjetos2.TpGrupo12.repositories.IMedicionRepository;
import com.TpObjetos2.TpGrupo12.services.IEventoService;
import com.TpObjetos2.TpGrupo12.services.IMedicionService;

//ESTO ES EL ABM :D
@Service("medicionService")
public class MedicionService implements IMedicionService {
	
	@Autowired
    @Qualifier("medicionRepository")
    private IMedicionRepository medicionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Medicion> getAll() {
        return medicionRepository.findAll();
    }

    @Override
    public MedicionModel insertOrUpdate(MedicionModel medicionModel) {
        Medicion medicion = medicionRepository.save(modelMapper.map(medicionModel, Medicion.class));
        return modelMapper.map(medicion, MedicionModel.class);
    }

    @Override
    public boolean remove(int id) {
        try{
            medicionRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    // esto sirve pa buscar cosas podemos agragerlo a lo que querramos
    @Override
	public Medicion findById(int id) {
		return medicionRepository.findByidMedicion(id);
	}
    
    //RECOLECTOR INTELIGENTE
    public static void cambiarEstadoRecolector (MedicionRecolector dispo, boolean nuevoEstado) {
    	dispo.setEstaLlenoAhora(nuevoEstado);
    }
    
    public static void cambiarEstadoRecolector (MedicionRecolector dispo) {
    	if (dispo.isEstaLlenoAhora() == true) {
    		dispo.setEstaLlenoAhora(false);
    	} else {
    		dispo.setEstaLlenoAhora(true);
    	}
    }
    
    public void chequearContenidoRecolector (MedicionRecolector reco) {
    	LocalTime chequeos = LocalTime.of(7, 30);
    	boolean estado;
    	while (LocalTime.now().isAfter(LocalTime.of(7, 0)) && 
    			LocalTime.now().isBefore(LocalTime.of(23, 0))){
    		if (LocalTime.now().equals(chequeos)) {
    			int boleano = (int)(Math.random()*2);
    			if (boleano == 0) {
    				estado = true;
    			} else {
    				estado = false;
    			}
    			if (reco.isEstaLlenoAhora() != estado && reco.isEstaLlenoAhora()==false) {
    				cambiarEstadoRecolector(reco);
    				Evento evento = new Evento("El techo esta lleno, se envia una notificacion para vaciar", LocalDateTime.of(LocalDate.now(), 
    						chequeos), reco.getDispositivo());
    			}
    			vaciarRecolector(reco,chequeos);
    			chequeos = chequeos.plusMinutes(30);
    		}
    	}
    }
    
    public void vaciarRecolector(MedicionRecolector reco, LocalTime hora) {
		cambiarEstadoRecolector(reco);
		System.out.println("\nLa basura fue sacada exitosamente.");
		Evento evento = new Evento("Se vacio el tacho", LocalDateTime.of(LocalDate.now(), 
				hora.plusMinutes((int)(Math.random()*(40-10+1)+10))), reco.getDispositivo());
	}
}
