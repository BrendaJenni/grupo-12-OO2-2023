package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TpObjetos2.TpGrupo12.models.MedicionHumedadModel;
import com.TpObjetos2.TpGrupo12.services.IMedicionHumedadService;
@Controller
@RequestMapping("/medicionhumedad")
public class MedicionHumedadController {

	@Autowired
    @Qualifier("medicionHumedadService")
    private IMedicionHumedadService medicionHumedadService;
	
	@GetMapping("/") //mi index
    public String index(Model model){
       model.addAttribute("dispositivos", medicionHumedadService.getAll());
       return "dispositivo/humedad";
    }
	
	
}
