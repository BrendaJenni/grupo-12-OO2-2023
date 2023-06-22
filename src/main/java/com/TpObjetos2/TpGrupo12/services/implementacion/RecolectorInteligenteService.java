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
    public DispositivoModel insertOrUpdateReco(Dispositivo dispositivoModel) {
        if (dispositivoModel != null) {
            Dispositivo dispositivoExistente = recolectorRepository.findById(dispositivoModel.getId());
            if (dispositivoExistente != null) {
                // actualizo es status del dispositivo
                dispositivoExistente.setActivo(false);

                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = recolectorRepository.save((RecolectorInteligente)dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
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
	public DispositivoModel agregarMedicion(MedicionRecolector medicion) {
		boolean estaLlenoAhora = medicion.isEstaLlenoAhora();
        LocalDateTime fecha = medicion.getFechaRegistro();
        Dispositivo dispo = medicion.getDispositivo();
		return agregarMedicion(dispo,fecha,estaLlenoAhora);
	}
    
    @Override
    public Dispositivo findByid(int id) {
        Dispositivo dispositivoOptional = recolectorRepository.findById(id);
        return dispositivoOptional;

    }

    @Override
    public DispositivoModel agregarEventos(Dispositivo dispositivoModel,Evento evento) {
        if (dispositivoModel != null) {
            Dispositivo dispositivoExistente = recolectorRepository.findById(dispositivoModel.getId());
            if (dispositivoExistente != null) {
                List<Evento> eventos = dispositivoExistente.getEventos();
                Evento eventosN = new Evento();

                eventosN.setDescripcion(evento.getDescripcion());
                eventosN.setFechaRegistro(evento.getFechaRegistro());
                eventosN.setDispositivo(dispositivoExistente);


                eventos.add(eventosN);

                dispositivoExistente.setEventos(eventos);;

                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = recolectorRepository.save((RecolectorInteligente)dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
       };
}