package com.TpObjetos2.TpGrupo12.services.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorAlumbradoRepository;
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;

@Service("sensorAlumbradoService")
public class SensorAlumbradoService implements ISensorAlumbradoService {


    @Autowired
    @Qualifier("sensorAlumbradoRepository")
    private ISensorAlumbradoRepository sensorAlumbradoRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<SensorAlumbrado> getAll() {
		return sensorAlumbradoRepository.findAll();
	}
	
    @Override
    public SensorAlumbradoModel insertOrUpdate(SensorAlumbradoModel sensorAlumbradoModel) {
        SensorAlumbrado sensorAlumbrado = new SensorAlumbrado();
        sensorAlumbrado.setId(sensorAlumbradoModel.getId());
        sensorAlumbrado.setNombre(sensorAlumbradoModel.getNombre());
        sensorAlumbrado.setActivo(sensorAlumbradoModel.isActivo());
        sensorAlumbrado.setEstacion(sensorAlumbradoModel.getEstacion());
        sensorAlumbrado.setEncendido(sensorAlumbradoModel.isEncendido());
        sensorAlumbrado.setObscuridadPor(sensorAlumbradoModel.getObscuridadPor());

        sensorAlumbrado = sensorAlumbradoRepository.save(sensorAlumbrado);
        return modelMapper.map(sensorAlumbrado, SensorAlumbradoModel.class);
    }

	@Override
    public DispositivoModel insertOrUpdatealum(Dispositivo dispositivoModel) {
        if (dispositivoModel != null) {
            Dispositivo dispositivoExistente = sensorAlumbradoRepository.findById(dispositivoModel.getId());
            if (dispositivoExistente != null) {
                // actualizo es status del dispositivo
                dispositivoExistente.setActivo(false);
               
                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = sensorAlumbradoRepository.save((SensorAlumbrado)dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
    }
	
	@Override
    public DispositivoModel agregarMedicion(Dispositivo dispositivoModel,LocalDateTime fecha,boolean estadoActual,double obscuridadActualPor) {
        if (dispositivoModel != null) {
            Dispositivo dispositivoExistente = sensorAlumbradoRepository.findById(dispositivoModel.getId());
            if (dispositivoExistente != null) {
                List<Medicion> mediciones = dispositivoExistente.getMediciones();
                
                MedicionAlumbrado medicion = new MedicionAlumbrado();
                
                medicion.setEstadoActual(estadoActual);
                medicion.setFechaRegistro(fecha);
                medicion.setOscuridadActualPor(obscuridadActualPor);
                medicion.setDispositivo(dispositivoExistente);
                
                mediciones.add(medicion);
                
                dispositivoExistente.setMediciones(mediciones);
               
                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = sensorAlumbradoRepository.save((SensorAlumbrado)dispositivoExistente);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
    }
	
	
	@Override
	public DispositivoModel agregarEventos(Dispositivo dispositivoModel,Evento evento) {
		if (dispositivoModel != null) {
            Dispositivo dispositivoExistente = sensorAlumbradoRepository.findById(dispositivoModel.getId());
            
            
            if (dispositivoExistente != null) {
                List<Evento> eventos = dispositivoExistente.getEventos();
                Evento eventosN = new Evento();
                
                eventosN.setDescripcion(evento.getDescripcion());
                eventosN.setFechaRegistro(evento.getFechaRegistro());
                eventosN.setDispositivo(dispositivoExistente);
               
                
                eventos.add(eventosN);
                
                dispositivoExistente.setEventos(eventos);;
               
                //genero este cambio para poder variasen el encendido del primer mostra y que se vea mejor en el front
                SensorAlumbrado setActivo = (SensorAlumbrado)dispositivoExistente;

                if(evento.getDescripcion().equals("Encender Luz")) {
                	
                	setActivo.setEncendido(true);;
                	
                }else {
                	
                	setActivo.setEncendido(false);;
                	
                }
                // lo gurado en la base de datos
                Dispositivo dispositivoActualizado = sensorAlumbradoRepository.save(setActivo);
                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
            }
        }
     return null;
		};

		
	@Override
	public Dispositivo findByid(int id) {
		Dispositivo dispositivoOptional = sensorAlumbradoRepository.findById(id);
        return dispositivoOptional;
		
	}
	
}

