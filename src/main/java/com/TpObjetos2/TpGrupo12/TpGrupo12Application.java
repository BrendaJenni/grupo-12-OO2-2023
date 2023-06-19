package com.TpObjetos2.TpGrupo12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;
@SpringBootApplication
public class TpGrupo12Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TpGrupo12Application.class, args);
		
	}
	
	@Autowired
	private ISensorEstacionamientoRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		SensorEstacionamiento estacionamiento = new SensorEstacionamiento();
		/*
		Dispositivo dispositivo1 = new Dispositivo("sensor calor", true);
		repository.save(dispositivo1);
		
		Dispositivo dispositivo2 = new Dispositivo("sensor luz", false);
		repository.save(dispositivo2);
		
		
		estacionamiento.setTam(8);
		List<Boolean> plazas = new ArrayList<Boolean>();
		for(int i=0;i<estacionamiento.getTam();i++) {
			plazas.add(false);
		}
		
		estacionamiento.setActivo(false);
		estacionamiento.setNombre("Estacionamiento");
		estacionamiento.setPlazas(plazas);
		
		repository.save(estacionamiento);
		System.out.println(estacionamiento.toString());*/
	}

}
