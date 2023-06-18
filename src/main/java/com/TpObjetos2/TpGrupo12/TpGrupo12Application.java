package com.TpObjetos2.TpGrupo12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
@SpringBootApplication
public class TpGrupo12Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TpGrupo12Application.class, args);
		
	}
	@Autowired
	private IDispositivoRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		Dispositivo dispositivo1 = new Dispositivo("sensor calor", true);
		repository.save(dispositivo1);
		
		Dispositivo dispositivo2 = new Dispositivo("sensor luz", false);
		repository.save(dispositivo2);
		
		*/
		
	}

}
