package com.TpObjetos2.TpGrupo12.components;

import java.time.LocalDate;
import java.time.*;
import java.time.LocalTime;
import java.util.List;

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
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;

@Component
public class FuncionesEstacionamiento {
	
	@Autowired
    @Qualifier("estacionamientoService")
    private ISensorEstacionamientoService estacionamientoService;
	
	
	//Alumbrado funcion que se va a ejecutar para buscar un dispositivo random y devolver su ultima medicion dependiendo de esta medicion se va a ejecutar un evento
		public MedicionEstacionamiento traerUltimaMediacion() {
		MedicionEstacionamiento medi; 
			
		List<SensorEstacionamiento> dispositivos = estacionamientoService.getAll();
        int id = (int) (Math.random()*dispositivos.size());
        
        //asigno el dispositivo a una variable dispositivo para poder consumir las mediciones
        Dispositivo buscar = estacionamientoService.findByid(dispositivos.get(id).getId());
        List<Medicion> mediciones = buscar.getMediciones();
        
        // si las mediciones estan vacias creamos la primera y devolvemos null lo usamos como flag para cuando llamemos a la funcion
        if(mediciones.size() == 0)  
        {
        	
        	medi = null;
        	// lo mandamos a generar la primera medicion del dispositivo con la luz apagada en la proxima variable cuando se vea nuevamente la hora y el porcentaje de luz se decidira si prenderla
        	estacionamientoService.agregarMedicion(buscar, LocalDateTime.now(), false);
        	
        }else {
        	
        	//en caso de que ya tenga mediciones se va a devolver la ultima generada
        	 medi = (MedicionEstacionamiento) mediciones.get(mediciones.size()-1);
        	 
        }
        return medi;
    }

	
	@Scheduled(fixedDelay=5000)
		public void mostarHola() {

			MedicionEstacionamiento implementar = this.traerUltimaMediacion();
			//LocalDate fecha = 
			LocalTime horaInicio = LocalTime.of(23, 0);
			LocalTime horaFin = LocalTime.of(07, 0);
			
			LocalTime horaMedAhora = implementar.getFechaRegistro().toLocalTime();
			
			
			if(implementar == null) {
				
				System.out.println("\n No tiene mediciones , agregamos la primera :D ");
				
			}
			
			// registro el horario en que debería estar prendida la luz
			//agregar or para que verifique el la obcuridadPor
			if((horaMedAhora.isAfter(horaInicio) && horaMedAhora.isBefore(horaFin))) 
			{
				//verifico si la luz esta apagada , si esta apagada la prendo y genero un evento
				if(implementar.isEstadoLibre()) {
					
					implementar.setEstadoLibre(false);
					
					//generar evento futuro
					Dispositivo dispo = implementar.getDispositivo();
					Evento agregar = new Evento("Estacionamiento se abre", implementar.getFechaRegistro(),dispo);
					estacionamientoService.agregarEventos(dispo, agregar);
					 System.out.println("\n AGREGAMOS EVENTO ESTACIONAMIENTO");
					
				}
						
					}
			else {
				
				
				if(!implementar.isEstadoLibre()) {
					
					implementar.setEstadoLibre(true);
					
					Dispositivo dispo = implementar.getDispositivo();
					Evento agregar =new Evento("Estacionamiento se cierra", implementar.getFechaRegistro(),dispo);
					 estacionamientoService.agregarEventos(dispo,agregar);
					 System.out.println("\n AGREGAMOS EVENTO ESTACIONAMIENTO");
					
				}
			}
				
				
				LocalDateTime fechanueva = implementar.getFechaRegistro();
				
				fechanueva = fechanueva.plusHours(1);
				
				//generamos un double random de 1 a 100 para poner en la obscuridad de la hora siguiente
				
				estacionamientoService.agregarMedicion(implementar.getDispositivo(), fechanueva, implementar.isEstadoLibre());
				 System.out.println("\n AGREGAMOS Medicion ESTACIONAMIENTO");
			};
		}