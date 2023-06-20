package com.TpObjetos2.TpGrupo12.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
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
		public MedicionAlumbrado traerUnRecoAleatorio() {
       
		MedicionAlumbrado medi = new MedicionAlumbrado(); 
			
		List<SensorAlumbrado> dispositivos = sensorAlumbradoService.getAll();
        int id = (int) (Math.random()*dispositivos.size());
        
        //asigno el dispositivo a una variable dispositivo para poder consumir las mediciones
        Dispositivo buscar = sensorAlumbradoService.findByid(dispositivos.get(id).getId());
        List<Medicion> mediciones = buscar.getMediciones();
        
        // si las mediciones estan vacias creamos la primera y devolvemos null lo usamos como flag para cuando llamemos a la funcion
        if(mediciones.size() == 0)  
        {
        	
        	medi = null;
        	System.out.println("No tiene mediciones agregamos la primera ");
        	// lo mandamos a generar la primera medicion del dispositivo con la luz apagada en la proxima variable cuando se vea nuevamente la hora y el porcentaje de luz se decidira si prenderla
        	sensorAlumbradoService.agregarMedicion(buscar, LocalDateTime.now(), false, 20);
        	
        }else {
        	
        	//en caso de que ya tenga mediciones se va a devolver la ultima generada
        	 medi = (MedicionAlumbrado) mediciones.get(mediciones.size()-1);
        	 
        }
        return medi;
    }

	
	@Scheduled(fixedDelay=5000)
		public void mostarHola() {

			MedicionAlumbrado implementar = this.traerUnRecoAleatorio();
			
		}

}
