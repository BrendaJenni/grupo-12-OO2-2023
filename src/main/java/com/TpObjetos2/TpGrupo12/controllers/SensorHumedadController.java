package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;

@Controller
@RequestMapping("/sensorhumedad")
public class SensorHumedadController {

	@Autowired
    @Qualifier("sensorHumedadService")
    private ISensorHumedadService sensorHumedadService;

	@GetMapping("/")
    public String index(Model model){
       model.addAttribute("sensor", sensorHumedadService.getAll());
       return "sensorHumedad/index";
    }
	
	
}
