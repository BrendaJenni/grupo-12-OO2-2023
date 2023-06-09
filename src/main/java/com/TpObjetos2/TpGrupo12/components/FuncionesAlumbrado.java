package com.TpObjetos2.TpGrupo12.components;

import java.time.*;
import java.time.LocalTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;

import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;

@Component
public class FuncionesAlumbrado {
	
	@Autowired
    @Qualifier("sensorAlumbradoService")
    private ISensorAlumbradoService sensorAlumbradoService;
	
	
	//Alumbrado funcion que se va a ejecutar para buscar un dispositivo random y devolver su ultima medicion dependiendo de esta medicion se va a ejecutar un evento
	public MedicionAlumbrado traerUltimaMediacion() {
		MedicionAlumbrado medi; 
			
		List<SensorAlumbrado> dispositivos = sensorAlumbradoService.getAll();
		int id = (int) (Math.random()*dispositivos.size());
	    
		//asigno el dispositivo a una variable dispositivo para poder consumir las mediciones
		Dispositivo buscar = sensorAlumbradoService.findByid(dispositivos.get(id).getId());
		List<Medicion> mediciones = buscar.getMediciones();
	
		// si las mediciones estan vacias creamos la primera y devolvemos null lo usamos como flag para cuando llamemos a la funcion
		if(mediciones.size() == 0) {
			medi = null;
			// lo mandamos a generar la primera medicion del dispositivo con la luz apagada en la proxima variable cuando se vea nuevamente la hora y el porcentaje de luz se decidira si prenderla
			// en caso de querer probar otro horario o hcerlo funcionar en tiempo real debemos borrar este LocalDateTime y poner LocalDateTime.now
			sensorAlumbradoService.agregarMedicion(buscar, LocalDateTime.of(LocalDate.of(2023, 06, 21), LocalTime.of(18, 00)), false, 20);
		}else {
			//dejo este campo solo para agregar 10 mediciones en cada dispositivo y que no rompa
			//ademas me fijo que el dispositvio este activo si no esta activo devuelvo null para que no se agregue nada
			if(mediciones.size() >= 10 || buscar.isActivo() == false) {
				//si entra aca ya no debemos agregar mediciones a este dispositivo
				medi = null;
			}else {		
				//en caso de que ya tenga mediciones se va a devolver la ultima generada
				medi = (MedicionAlumbrado) mediciones.get(mediciones.size()-1);
	    	} 
	    }
	    return medi;
	}
	
	@Scheduled(fixedDelay=5000)
	public void verificarOscuridadYHora() {	
		MedicionAlumbrado implementar = this.traerUltimaMediacion();
		//LocalDate fecha = 
		LocalTime horaInicio = LocalTime.of(23, 00);
		LocalTime horaFin = LocalTime.of(7, 00);
		
		if(implementar == null) {
			// si entra aca hay 3 escenarios
			// 1 - ya no hay que agregar mas dispositivos
			// 2 - tenemos que agregar el primer dispositivo porque la medicion esta vacia esto se hace en la linea 47
			// 3- el dispositivo esta desactivado	
		}else {
			LocalTime horaMedAhora = implementar.getFechaRegistro().toLocalTime();
			// registro el horario en que debería estar prendida la luz
			//agregar or para que verifique el la obcuridadPor
			if((horaMedAhora.isAfter(horaInicio) || horaMedAhora.isBefore(horaFin)) || horaMedAhora.equals(horaInicio)){
				//verifico si la luz esta apagada , si esta apagada la prendo y genero un evento
				if(implementar.isEstadoActual() == false) {
					implementar.setEstadoActual(true);
					//envio el envento al dispositivo para que se agregue correctamente
					Dispositivo dispo = implementar.getDispositivo();
					Evento agregar = new Evento("Encender Luz", implementar.getFechaRegistro(),dispo);
					sensorAlumbradoService.agregarEventos(dispo, agregar);
					System.out.println("\n AGREGAMOS EVENTO");
				} 
			} else if(implementar.getOscuridadActualPor() > 70) {	
				if(implementar.isEstadoActual() == false) {	
					implementar.setEstadoActual(true);
					//envio el envento al dispositivo para que se agregue correctamente
					Dispositivo dispo = implementar.getDispositivo();
					Evento agregar = new Evento("Encender Luz", implementar.getFechaRegistro(),dispo);
					sensorAlumbradoService.agregarEventos(dispo, agregar);
					System.out.println("\n AGREGAMOS EVENTO");
				}			
			}else if(implementar.isEstadoActual() == true) {
				implementar.setEstadoActual(false);
				//envio el envento al dispositivo para que se agregue correctamente
				Dispositivo dispo = implementar.getDispositivo();
				Evento agregar =new Evento("Apagar Luz", implementar.getFechaRegistro(),dispo);
				sensorAlumbradoService.agregarEventos(dispo,agregar);
				System.out.println("\n AGREGAMOS EVENTO");			 
			}
			List<SensorAlumbrado> dispositivos = sensorAlumbradoService.getAll();
			boolean check = this.medicionesCompletas(dispositivos);
			//llamo a la funcion para entender si las mediciones estan completas si es asi no agregamos nuevas
			if(check ==false) {
				LocalDateTime fechanueva = implementar.getFechaRegistro();	
				fechanueva = fechanueva.plusHours(1);
				//generamos un double random de 1 a 100 para poner en la obscuridad de la hora siguiente
				double nuevaObscuridad = Math.random()*100+1;
				sensorAlumbradoService.agregarMedicion(implementar.getDispositivo(), fechanueva, implementar.isEstadoActual(), nuevaObscuridad);
				System.out.println("\n AGREGAMOS MEDICION");					
			}
		}
	}
					
	public boolean medicionesCompletas(List<SensorAlumbrado> dispo) {	
		boolean devolver =  false;
		List<SensorAlumbrado> dispositivos = sensorAlumbradoService.getAll();
		//reviso todas las mediciones para ver si estan completas
	    int cantCompletos = 0;
	    for (int i = 0 ; i < dispositivos.size() ; i++) {	
	    	if(dispositivos.get(i).getMediciones().size() == 10) {	
	    		cantCompletos = cantCompletos + 1;	
	    	}
	    }
	    //si las mediciones etan completas devuelvo true y no se van a generar mediciones nuevas
	    if(cantCompletos == dispositivos.size()) {    	
	        devolver = true;
	    }
	    return devolver;	
	}
		
}