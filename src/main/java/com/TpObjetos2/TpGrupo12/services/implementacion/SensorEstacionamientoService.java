package com.TpObjetos2.TpGrupo12.services.implementacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		
		public SensorEstacionamiento insertOrUpdateEst(SensorEstacionamiento dispositivoModel) {
	        if (dispositivoModel != null) {
	            SensorEstacionamiento dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
	            if (dispositivoExistente != null) {
	                // actualizo es status del dispositivo
	                dispositivoExistente.setActivo(false);
	               
	                // lo gurado en la base de datos
	                SensorEstacionamiento dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)dispositivoExistente);
	                return modelMapper.map(dispositivoActualizado, SensorEstacionamiento.class);
	            }
	        }
	     return null;
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
		       estacionamiento.setLibres(estacionamiento.calcularPlazasLibres());
		       
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
	                medicion.setEstadoLibre(estadoLibre);
	                
	                mediciones.add(medicion);
	                
	                dispositivoExistente.setMediciones(mediciones);
	               
	                // lo gurado en la base de datos
	                Dispositivo dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)dispositivoExistente);
	                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
	            }
	        }
	     return null;
	    }
		
		@Override
	    public DispositivoModel agregarEvento(SensorEstacionamiento dispositivoModel,LocalDateTime fecha,String descripcion) {
	        if (dispositivoModel != null) {
	            SensorEstacionamiento dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
	            if (dispositivoExistente != null) {
	                List<Evento> eventos = dispositivoExistente.getEventos();
	                
	                Evento evento = new Evento();
	                
	                evento.setDescripcion(descripcion);
	                evento.setDispositivo(dispositivoModel);
	                evento.setFechaRegistro(fecha);
	                
	                eventos.add(evento);
	                
	                dispositivoExistente.setEventos(eventos);
	               
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
				
				public DispositivoModel agregarEventoAutomatico(SensorEstacionamiento estacionamiento) {
					LocalDate dia = LocalDate.now();
					LocalTime inicio = LocalTime.of(6, 0);
					LocalTime fin = LocalTime.of(22, 0);
					
					List<Evento> eventos = estacionamiento.getEventos();
					Random random = new Random();
					int pos = random.nextInt(estacionamiento.getPlazas().size());
					
					for(int i=0;i<estacionamiento.getPlazas().size();i++) {
						if(pos==i) {
							if(estacionamiento.getPlazas().get(i)==true) {
								estacionamiento.getPlazas().set(i, false);
							}
							if(estacionamiento.getPlazas().get(i)==false) {
								estacionamiento.getPlazas().set(i, true);
							}
						}
					}
					Evento evento = new Evento("cambio de plazas", LocalDateTime.now(), estacionamiento);
					eventos.add(evento);
					
					estacionamiento.setEventos(eventos);
					
					Dispositivo dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)estacionamiento);
	                return modelMapper.map(dispositivoActualizado, DispositivoModel.class);
					
				}
				/*
				public void agregarMedicionAutomatic(SensorEstacionamiento estacionamiento) {
					List<Medicion> mediciones = estacionamiento.getMediciones();
					
					for(int i=0;i<mediciones.size();i++) {
						if(mediciones.get(i)estacionamiento.isActivo()) {
							
						}
					}
				}*/

		@Override
		public SensorEstacionamiento findByid(int id) {
			SensorEstacionamiento dispositivoOptional = estacionamientoRepository.findById(id);
	        return dispositivoOptional;
		}

		@Override
		public List<Boolean> getPlazas() {
			List<Boolean> plazas = estacionamientoRepository.findByPlazas(false);
			System.out.println(plazas);
			return plazas;
		}

		@Override
		public SensorEstacionamiento insertOrUpdate(SensorEstacionamiento estacionamientoModel) {
			estacionamientoRepository.save(estacionamientoModel);
			return modelMapper.map(estacionamientoModel, estacionamientoModel.getClass());
		}

		//@Override
		/*
		public SensorEstacionamientoModel insertOrUpdateEst(SensorEstacionamientoModel dispositivoModel, List<Boolean> plazas) {
		    if (dispositivoModel != null) {
		        SensorEstacionamiento dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
		        if (dispositivoExistente != null) {
		            dispositivoExistente.setPlazas(actualizarPlazas(dispositivoModel));

		            SensorEstacionamiento dispositivoActualizado = estacionamientoRepository.save(dispositivoExistente);
		            return modelMapper.map(dispositivoActualizado, SensorEstacionamientoModel.class);
		        }
		    }
		    return null;
		}*/

		@Override
		public DispositivoModel insertOrUpdateEst(Dispositivo dispositivoModel, List<Boolean> plazas) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public List<Boolean> actualizarPlazas(SensorEstacionamientoModel estacionamiento) {
			List<SensorEstacionamiento> estacionamientos = estacionamientoRepository.findAll();
			List<Integer> listaIds = new ArrayList<>();
			/*
			System.out.println("Tam lista=" + estacionamientos.size());
			//List<Dispositivo> estacionamientos = new ArrayList<>();
			
			for(int i=0;i<estacionamientos.size();i++) {
				System.out.println(estacionamientos.get(i).toString());
			}
	        
	        for(int i=0;i<estacionamientos.size();i++) {
	        	listaIds.add(estacionamientos.get(i).getId());
	        }
	        
	        Random random = new Random();

	        // Generar un número aleatorio entre 0 y 9
	        int numeroAleatorio = random.nextInt(estacionamientos.size());
	        System.out.println("Número aleatorio: " + numeroAleatorio);
	        
	        int posicion = listaIds.get(numeroAleatorio);
	        
	        SensorEstacionamiento estacionamiento = estacionamientoRepository.findById(posicion);
	        
	        System.out.println("Estacionamiento: " + estacionamiento.toString());
	        
	        int booleano = random.nextInt(2);
	        
	        for(int i=0;i<estacionamiento.getPlazas().size();i++) {
	        	System.out.println("Plazas originales= " + estacionamiento.getPlazas().get(i));
	        }
	        */
			 Random random = new Random();
			int booleano = random.nextInt(2);
			//SensorEstacionamientoModel estacionamiento1=new SensorEstacionamientoModel();
	        for(int i=0;i<estacionamiento.getTam();i++) {
	        	if(booleano==0) {
	        		estacionamiento.getPlazas().set(i, false);
	        	}else {
	        		estacionamiento.getPlazas().set(i, true);
	        	}
	        	booleano = random.nextInt(2);
	        }
	        
	        for(int i=0;i<estacionamiento.getPlazas().size();i++) {
	        	System.out.println("Plazas cambiadass= " + estacionamiento.getPlazas().get(i));
	        }
	        
	        SensorEstacionamientoModel model = new SensorEstacionamientoModel();
	        model.setPlazas(estacionamiento.getPlazas());
	        //model.setActivo(true);
	        //model.setEventos(estacionamiento.getEventos());
	        //model.setLibres(estacionamiento.getLibres());
	        //model.setMediciones(estacionamiento.getMediciones());
	        //model.setNombre(estacionamiento.getNombre());
	        //model.setTam(estacionamiento.getTam());
	        
	        //this.insertOrUpdateEst(model, estacionamiento.getPlazas());
	        
	        return estacionamiento.getPlazas();
		}
		
		@Override
	    public SensorEstacionamientoModel insertOrUpdateEst(SensorEstacionamientoModel dispositivoModel) {
			List<Boolean> plazas = actualizarPlazas(dispositivoModel);
	        if (dispositivoModel != null) {
	            SensorEstacionamiento dispositivoExistente = estacionamientoRepository.findById(dispositivoModel.getId());
	            if (dispositivoExistente != null) {
	                // actualizo es status del dispositivo
	            	
	               dispositivoExistente.setPlazas(plazas);
	               
	                // lo gurado en la base de datos
	                SensorEstacionamiento dispositivoActualizado = estacionamientoRepository.save((SensorEstacionamiento)dispositivoExistente);
	                return modelMapper.map(dispositivoActualizado, SensorEstacionamientoModel.class);
	            }
	        }
	     return null;
	    }

		@Override
		/*public DispositivoModel agregarMedicion(Dispositivo dispositivoModel, LocalDateTime fecha,
				boolean estadoLibre) {
			
			return null;
		}*/


		
		//public SensorEstacionamientoModel insertOrUpdate(SensorEstacionamientoModel estacionamientoModel) {
			//SensorEstacionamiento estacionamiento = estacionamientoRepository.save(modelMapper.map(estacionamientoModel, SensorEstacionamiento.class));
			//return modelMapper.map(estacionamiento, SensorEstacionamientoModel.class);
		//}

		public MedicionEstacionamiento traerUltimaMedicion() {
		       
			MedicionEstacionamiento medicion; 
			
			List<SensorEstacionamiento> estacionamientos = estacionamientoRepository.findAll();
	        int id = (int) (Math.random()*estacionamientos.size());
	        SensorEstacionamiento buscar = estacionamientoRepository.findById(estacionamientos.get(id).getId());
	        System.out.println(buscar.toString());
	        List<Medicion> mediciones = buscar.getMediciones();
	        if(mediciones.size() == 0)  
	        {
	        	medicion = null;
	        	this.agregarMedicion(buscar, LocalDateTime.now(), false);
	        }else {
	        	 medicion = (MedicionEstacionamiento) mediciones.get(mediciones.size()-1);
	        }
	        return medicion;
        /*
        SensorEstacionamientoModel model = new SensorEstacionamientoModel();
        
        System.out.println("Cambio de estados");
        estacionamiento.setPlazas(estacionamientoService.actualizarPlazas(model));
        model.setPlazas(estacionamiento.getPlazas());
        model.setNombre("Est");
        estacionamientoService.insertOrUpdate(model);
        
        Evento evento = new Evento("cambio en estados de plazas", this.traerUltimaMediacion().getFechaRegistro(), model);
        estacionamientoService.agregarEventos(model, evento);
		System.out.println("\n AGREGAMOS EVENTO ESTACIONAMIENTO");
        */
    }

		@Override
		public DispositivoModel agregarMedicion(SensorEstacionamientoModel dispositivoModel, LocalDateTime fecha,
				boolean estadoLibre) {
			
			return null;
		}

	}
