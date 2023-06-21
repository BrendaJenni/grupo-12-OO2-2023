package com.TpObjetos2.TpGrupo12.components;

import java.time.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.MedicionEstacionamiento;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;
import com.TpObjetos2.TpGrupo12.models.*;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;


@Component
public class FuncionesEstacionamiento {
	
	@Autowired
    @Qualifier("estacionamientoService")
    private ISensorEstacionamientoService estacionamientoService;
	
	@Autowired
	@Qualifier("dispositivoService")
	private IDispositivoService dispositivoService;
	
	//private ISensorEstacionamientoRepository repository;
	/*
		public MedicionEstacionamiento traerUltimaMedicion() {
       
			MedicionEstacionamiento medicion; 
			
			List<SensorEstacionamiento> estacionamientos = estacionamientoService.getAll();
	        int id = (int) (Math.random()*estacionamientos.size());
	        Dispositivo buscar = estacionamientoService.findByid(estacionamientos.get(id).getId());
	        List<Medicion> mediciones = buscar.getMediciones();
	        if(mediciones.size() == 0)  
	        {
	        	medicion = null;
	        	estacionamientoService.agregarMedicion(buscar, LocalDateTime.now(), false);
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
        
    

	
	@Scheduled(fixedDelay=5000)
	
		public void runJob() {
		MedicionEstacionamiento implementar = estacionamientoService.traerUltimaMedicion();
		
		LocalTime horaInicio = LocalTime.of(6, 0);
		LocalTime horaFin = LocalTime.of(22, 0);
		Random random = new Random();
		
		if(implementar == null) {
		}else {
			LocalTime horaMedAhora = implementar.getFechaRegistro().toLocalTime();
			if((horaMedAhora.isAfter(horaInicio) && horaMedAhora.isBefore(horaFin))) {
						
				if(implementar.isEstadoLibre()) {
							
					SensorEstacionamiento estacionamiento = (SensorEstacionamiento)implementar.getDispositivo();
					estacionamiento.setActivo(true);
					implementar.setDispositivo(estacionamiento);
					Evento agregar = new Evento("Abrir estacionamiento", implementar.getFechaRegistro(),implementar.getDispositivo());
					estacionamientoService.insertOrUpdate(estacionamiento);
					estacionamientoService.agregarEventos(estacionamiento, agregar);
						
				}else if(!implementar.isEstadoLibre()) {
					SensorEstacionamiento estacionamiento = (SensorEstacionamiento)implementar.getDispositivo();
					estacionamiento.setActivo(true);
					//List<Boolean> plazas = estacionamiento.getPlazas();
					//estacionamiento.setActivo(false);
					estacionamiento.inicializarPlazas();
					implementar.setDispositivo(estacionamiento);
					Evento agregar = new Evento("Cerrar estacionamiento", implementar.getFechaRegistro(),implementar.getDispositivo());
					estacionamientoService.insertOrUpdate(estacionamiento);
					estacionamientoService.agregarEventos(estacionamiento, agregar);
				}else {
					
					implementar.setEstadoLibre(false);
					Evento agregar = new Evento("Cerrar estacionamiento", implementar.getFechaRegistro(),implementar.getDispositivo());
					estacionamientoService.agregarEventos(implementar.getDispositivo(), agregar);
				}
					
			}else {
				implementar.setEstadoLibre(false);
				Evento agregar = new Evento("Cerrar estacionamiento", implementar.getFechaRegistro(),implementar.getDispositivo());
				estacionamientoService.agregarEventos(implementar.getDispositivo(), agregar);
			}
			List<SensorEstacionamiento> dispositivos = estacionamientoService.getAll();
			
			boolean check = this.medicionesCompletas(dispositivos);
			
			if(check ==false) {
			LocalDateTime fechanueva = implementar.getFechaRegistro();
			fechanueva = fechanueva.plusHours(1);
			//estacionamientoService.agregarMedicion(implementar.getDispositivo(), fechanueva, implementar.isEstadoLibre());
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
		/*
		System.out.println("hoia");
		
		List<SensorEstacionamiento> estacionamientos = estacionamientoService.getAll();
		List<Integer> listaIds = new ArrayList<>();
		
		
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
        
        SensorEstacionamiento estacionamiento = estacionamientoService.findByid(posicion);
        
        System.out.println("Estacionamiento: " + estacionamiento.toString());
        
        estacionamientoService.agregarEventoAutomatico(estacionamiento);
		//estacionamientoService.actualizarPlazas();
		/*MedicionEstacionamiento implementar = this.traerUltimaMediacion();
		//LocalDate fecha = 
		LocalTime horaInicio = LocalTime.of(23, 0);
		LocalTime horaFin = LocalTime.of(07, 0);
		
		LocalTime horaMedAhora = implementar.getFechaRegistro().toLocalTime();
		
		
		if(implementar == null) {
			
			System.out.println("\n No tiene mediciones , agregamos la primera :D ");
			
		}
		
			if((horaMedAhora.isAfter(horaInicio) && horaMedAhora.isBefore(horaFin))) 
			{
				if(implementar.isEstadoLibre() == false) {
					
					implementar.setEstadoLibre(true);
					
					Dispositivo dispo = implementar.getDispositivo();
					Evento agregar = new Evento("Cambio de estado", implementar.getFechaRegistro(),dispo);
					estacionamientoService.agregarEventos(dispo, agregar);
					System.out.println("\n AGREGAMOS EVENTO ESTACIONAMIENTO");
					
				}
						
					}
			else {
				
				if(implementar.isEstadoLibre() == true) {
					
					implementar.setEstadoLibre(false);
					
					Dispositivo dispo = implementar.getDispositivo();
					Evento agregar =new Evento("Cambio de estado", implementar.getFechaRegistro(),dispo);
					 estacionamientoService.agregarEventos(dispo,agregar);
					 System.out.println("\n AGREGAMOS EVENTO ESTACIONAMIENTO");
					
				}
			}
				
				
				
				LocalDateTime fechanueva = implementar.getFechaRegistro();
				
				fechanueva = fechanueva.plusHours(1);
				
				estacionamientoService.agregarMedicion(implementar.getDispositivo(), fechanueva, implementar.isEstadoLibre());
				 System.out.println("\n AGREGAMOS Medicion ESTACIONAMIENTO");
				*/
				
		
		
			};
			
			
			
			
			
			
		