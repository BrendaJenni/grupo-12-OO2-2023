package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionRecolectorModel;
import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;
import com.TpObjetos2.TpGrupo12.models.SensorRecolectorModel;
import com.TpObjetos2.TpGrupo12.repositories.IRecolectorInteligenteRepository;
import com.TpObjetos2.TpGrupo12.repositories.ISensorHumedadRepository;
import com.TpObjetos2.TpGrupo12.services.IRecolectorInteligenteService;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;

@Service ("recolectorInteligenteService")
public class RecolectorInteligenteService implements IRecolectorInteligenteService {
	
	@Autowired
    @Qualifier("recolectorInteligenteRepository")
    private IRecolectorInteligenteRepository recolectorRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
	public List<RecolectorInteligente> getAll() {
		return recolectorRepository.findAll();
	}

    @Override
	public SensorRecolectorModel insertOrUpdate(DispositivoModel dispositivoModel) {
    	RecolectorInteligente recolector = recolectorRepository.save(modelMapper.map(dispositivoModel, RecolectorInteligente.class));
        return modelMapper.map(recolector, SensorRecolectorModel.class);
	}
    
    @Override
    public DispositivoModel agregarMedicion(Dispositivo dispositivoModel,LocalDateTime fecha,boolean estaLlenoAhora) {
        if (dispositivoModel != null) {
            Dispositivo dispositivoExistente = recolectorRepository.findById(dispositivoModel.getId());
            if (dispositivoExistente != null) {
                List<Medicion> mediciones = dispositivoExistente.getMediciones();

                MedicionRecolector medicion = new MedicionRecolector();

                medicion.setEstaLlenoAhora(estaLlenoAhora);
                medicion.setFechaRegistro(fecha);
                medicion.setDispositivo(dispositivoExistente);

                mediciones.add(medicion);

                dispositivoExistente.setMediciones(mediciones);

                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = recolectorRepository.save((RecolectorInteligente)dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
    }
    
    @Override
    public Dispositivo findByid(int id) {
        Dispositivo dispositivoOptional = recolectorRepository.findById(id);
        return dispositivoOptional;

    }
    
	/*
	//RECOLECTOR INTELIGENTE
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
    
    public void chequearContenidoRecolector (MedicionRecolector reco) {
    	LocalTime ahora = reco.getFechaRegistro().toLocalTime();
    	LocalDate fecha = reco.getFechaRegistro().toLocalDate();
    	LocalTime chequeos = ahora;
    	int boleano;
    	boolean estado;
    	while (ahora.isAfter(LocalTime.of(7, 0)) && ahora.isBefore(LocalTime.of(9, 0))){
    		if (ahora.equals(chequeos)) {
    			if (reco.isEstaLlenoAhora() == true) {
    				Evento e = new Evento ("El techo esta lleno, se envia una notificacion para vaciar", LocalDateTime.of(fecha, 
    						ahora), reco.getDispositivo());
    				reco.getDispositivo().getEventos().add(e);
    				vaciarRecolector(reco,chequeos);
    				cambiarEstadoRecolector(reco);
    			}
    			boleano = (int)(Math.random()*2);
    			if (boleano == 0) {
    				estado = true;
    			} else {
    				estado = false;
    			}
    			reco = cambiarEstadoRecolector(reco);
    			MedicionRecolector m = new MedicionRecolector(reco.getDispositivo(),LocalDateTime.of(fecha, ahora),estado);
    			reco.getDispositivo().getMediciones().add(reco);
    			chequeos = chequeos.plusMinutes(30);
    		}
    	}
    }
    
    public void vaciarRecolector(MedicionRecolector reco, LocalTime hora) {
    	Evento e = new Evento("Se vacio el tacho", LocalDateTime.of(reco.getFechaRegistro().toLocalDate(), 
				hora.plusMinutes((int)(Math.random()*(20-10+1)+10))), reco.getDispositivo());
	}*/
}
