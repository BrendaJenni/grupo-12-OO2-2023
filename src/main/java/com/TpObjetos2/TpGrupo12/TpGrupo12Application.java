package com.TpObjetos2.TpGrupo12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.components.FuncionesRecolector;
@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "com.TpObjetos2.TpGrupo12.components")
public class TpGrupo12Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TpGrupo12Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		SpringApplication.run(FuncionesRecolector.class, args);
	}

}
