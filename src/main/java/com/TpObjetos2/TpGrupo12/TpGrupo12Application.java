package com.TpObjetos2.TpGrupo12;

import org.springframework.beans.factory.annotation.Autowired;
import com.TpObjetos2.TpGrupo12.entities.*;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.repositories.IEventoRepository;
import com.TpObjetos2.TpGrupo12.repositories.IMedicionRepository;
import com.TpObjetos2.TpGrupo12.repositories.ISensorAlumbradoRepository;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;
import com.TpObjetos2.TpGrupo12.repositories.IUserRepository;

import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class TpGrupo12Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TpGrupo12Application.class, args);
		
	}
	
	@Autowired
	private IDispositivoRepository repository;
	
	@Autowired
	private IMedicionRepository medicionRepository;
	
	@Autowired
	private ISensorAlumbradoRepository alumbradoRepository;
	
	@Autowired
	private IEventoRepository eventoRepository;
	
	@Autowired
	private ISensorEstacionamientoRepository estacionamientoRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		//HAY UN ERROR TIPO Medicion.getFechaRegistro() BECAUSE Implementer is null LA PRIMERA VEZ QUE
		//SE CORRE EL PROGRAMA
		
		//SAVE DISPOSITIVOS GENÉRICOS
		Dispositivo dispositivo1 = new Dispositivo("estacionamiento", true);
		repository.save(dispositivo1);
		
		Dispositivo dispositivo2 = new Dispositivo("alumbrado", false);
		repository.save(dispositivo2);
		
		List<Boolean> plazas = null;
		SensorEstacionamiento estacionamiento = new SensorEstacionamiento("Estacionamiento común", true, 8, 8, plazas);
		estacionamiento.inicializarPlazas();
		estacionamientoRepository.save(estacionamiento);
		
		//SAVE SENSORALUMBRADO
		SensorAlumbrado alumbrado = new SensorAlumbrado("Alumbrado Hernandez", true, "invierno", true, 50);
		alumbradoRepository.save(alumbrado);
		
		//SAVE EVENTO
		Evento evento = new Evento("Evento inicial", LocalDateTime.now(), alumbrado);
		eventoRepository.save(evento);
		
		//SAVE MEDICION GENÉRICO
		Medicion medicion = new Medicion(dispositivo2, LocalDateTime.now());
		medicionRepository.save(medicion);
		
		//SAVE MEDICIONALUMBRADO
		MedicionAlumbrado medicionAlumbrado = new MedicionAlumbrado(dispositivo2, LocalDateTime.now(), true, 25);
		medicionRepository.save(medicionAlumbrado);
		
		
	}

}
