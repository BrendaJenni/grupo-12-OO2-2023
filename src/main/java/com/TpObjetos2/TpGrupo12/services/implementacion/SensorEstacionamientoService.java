package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.TpObjetos2.TpGrupo12.entities.MedicionEstacionamiento;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;

	@Service("estacionamientoService")
	public class SensorEstacionamientoService implements ISensorEstacionamientoService{
		@Autowired
		private ISensorEstacionamientoRepository estacionamientoRepository;
		
		private ModelMapper modelMapper = new ModelMapper();
		
		@Override
		public List<SensorEstacionamiento> getAll() {
			return estacionamientoRepository.findAll();
		}

		@Override
		public SensorEstacionamientoModel insertOrUpdate(SensorEstacionamientoModel estacionamientoModel) {
			SensorEstacionamiento estacionamiento = estacionamientoRepository.save(modelMapper.map(estacionamientoModel, SensorEstacionamiento.class));
			return modelMapper.map(estacionamiento, SensorEstacionamientoModel.class);
		}
		
		@Override
	    public DispositivoModel insertOrUpdateEst(Dispositivo dispositivoModel) {
	        if (dispositivoModel != null) {
	            Dispositivo dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
	            if (dispositivoExistente != null) {
	                // actualizo es status del dispositivo
	                dispositivoExistente.setActivo(false);
	               
	                // lo gurado en la base de datos
	                Dispositivo dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)dispositivoExistente);
	                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
	            }
	        }
	     return null;
	    }
		
		public List<SensorEstacionamiento> traerstacionamientosActivos() {
			List<SensorEstacionamiento> dispositivos = estacionamientoRepository.findAll();
			List<SensorEstacionamiento> estacionamientos = new ArrayList<>();
			for (SensorEstacionamiento dispositivo : dispositivos) {
				if (dispositivo.isActivo() == true) {
					estacionamientos.add(dispositivo);
				}
			}
			return estacionamientos;
		}
		
		public SensorEstacionamiento crearEstacionamientoConPlazas() {
			SensorEstacionamiento estacionamiento = new SensorEstacionamiento();
		       List<Boolean> plazas = new ArrayList<Boolean>();
		       estacionamiento.setPlazas(plazas);
		       estacionamiento.inicializarPlazas();
		       
		       return estacionamiento;
		}

		@Override
		public boolean remove(int id) {
			 try{
		            estacionamientoRepository.deleteById(id);
		            return true;
		        } catch(Exception e){
		            return false;
		        }
		    }
		
		@Override
	    public DispositivoModel agregarMedicion(SensorEstacionamiento dispositivoModel,LocalDateTime fecha,boolean estadoLibre) {
	        if (dispositivoModel != null) {
	            SensorEstacionamiento dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
	            if (dispositivoExistente != null) {
	                List<Medicion> mediciones = dispositivoExistente.getMediciones();
	                
	                MedicionEstacionamiento medicion = new MedicionEstacionamiento();
	                
	                medicion.setEstadoLibre(estadoLibre);
	                medicion.setFechaRegistro(fecha);
	                medicion.setDispositivo(dispositivoExistente);
	                
	                mediciones.add(medicion);
	                
	                dispositivoExistente.setMediciones(mediciones);
	               
	                // lo gurado en la base de datos
	                Dispositivo dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)dispositivoExistente);
	                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
	            }
	        }
	     return null;
	    }
		
		public MedicionEstacionamiento crearMedicions() {
			MedicionEstacionamiento estacionamiento = new MedicionEstacionamiento();
		       
		       return estacionamiento;
		}
		
		public DispositivoModel agregarEventos(Dispositivo dispositivoModel,Evento evento) {
			if (dispositivoModel != null) {
	            Dispositivo dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
	            if (dispositivoExistente != null) {
	                List<Evento> eventos = dispositivoExistente.getEventos();
	                Evento eventosN = new Evento();
	                
	                eventosN.setDescripcion(evento.getDescripcion());
	                eventosN.setFechaRegistro(evento.getFechaRegistro());
	                eventosN.setDispositivo(dispositivoExistente);
	               
	                
	                eventos.add(eventosN);
	                
	                dispositivoExistente.setEventos(eventos);
	               
	                // lo gurado en la base de datos
	                Dispositivo dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)dispositivoExistente);
	                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
	            }
	        }
	     return null;
			};
			
			
			public DispositivoModel agregarEventos(Dispositivo dispositivoModel,String descripcion, LocalDateTime fecha) {
				if (dispositivoModel != null) {
		            Dispositivo dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
		            if (dispositivoExistente != null) {
		                List<Evento> eventos = dispositivoExistente.getEventos();
		                Evento eventosN = new Evento();
		                
		                eventosN.setDescripcion(descripcion);
		                eventosN.setFechaRegistro(fecha);
		                eventosN.setDispositivo(dispositivoExistente);
		               
		                
		                eventos.add(eventosN);
		                
		                dispositivoExistente.setEventos(eventos);;
		               
		                // lo gurado en la base de datos
		                Dispositivo dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)dispositivoExistente);
		                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
		            }
		        }
		     return null;
				};

		@Override
		public Dispositivo findByid(int id) {
			Dispositivo dispositivoOptional = estacionamientoRepository.findById(id);
	        return dispositivoOptional;
		}

		@Override
		public List<Boolean> getPlazas() {
			List<Boolean> plazas = estacionamientoRepository.findByPlazas(false);
			System.out.println(plazas);
			return plazas;
		}

		@Override
		public DispositivoModel agregarMedicion(Dispositivo dispositivoModel, LocalDateTime fecha,
				boolean estadoLibre) {
			// TODO Auto-generated method stub
			return null;
		}

	}

