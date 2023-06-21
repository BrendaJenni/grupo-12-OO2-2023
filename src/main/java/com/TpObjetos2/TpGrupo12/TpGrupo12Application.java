package com.TpObjetos2.TpGrupo12;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;


//@EnableScheduling
@SpringBootApplication
public class TpGrupo12Application{

	public static void main(String[] args) {
		SpringApplication.run(TpGrupo12Application.class, args);
	}

}
