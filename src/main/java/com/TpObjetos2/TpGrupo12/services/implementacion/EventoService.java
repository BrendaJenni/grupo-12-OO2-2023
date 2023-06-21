package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.repositories.IEventoRepository;
import com.TpObjetos2.TpGrupo12.services.IEventoService;

@Service("eventoService")
public class EventoService implements IEventoService {

	@Autowired
    @Qualifier("eventoRepository")
    private IEventoRepository eventoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Evento> getAll() {
        return eventoRepository.findAll();
    }
    
    @Override
    public List<Evento> getEventosEstacionamiento() {
        List<Evento> eventos = eventoRepository.findAll();
        List<Evento> eventosEst = new ArrayList<>();
        for(int i=0;i<eventos.size();i++) {
        	if(eventos.get(i).getDispositivo() instanceof SensorEstacionamiento) {
        		eventosEst.add(eventos.get(i));
        	}
        }
        return eventosEst;
    }

    @Override
    public EventoModel insertOrUpdate(EventoModel eventoModel) {
        Evento evento = eventoRepository.save(modelMapper.map(eventoModel, Evento.class));
        return modelMapper.map(evento, EventoModel.class);
    }
    

    @Override
    public boolean remove(int id) {
        try{
            eventoRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    @Override
	public Evento findById(int id) {
		return eventoRepository.findByidEvento(id);
	}
	
}
