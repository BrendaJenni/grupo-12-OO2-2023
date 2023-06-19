package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;

@Controller
@RequestMapping("/sensorhumedad")
public class SensorHumedadController {

	@Autowired
    @Qualifier("sensorHumedadService")
    private ISensorHumedadService sensorHumedadService;

	@GetMapping("/") //mi index
    public String index(Model model){
       model.addAttribute("dispositivos", sensorHumedadService.getAll());
       return "dispositivo/humedad";
    }
	
	@PostMapping("crearsensor")
    public String createAlum(@ModelAttribute("dispositivo") SensorHumedadModel sensorHumedadModel) {
		sensorHumedadService.insertOrUpdate(sensorHumedadModel);
        return "redirect:";
    }
	@PostMapping("bajaLogica")
	public String eliminarSensor(@ModelAttribute("dispositivo") SensorHumedadModel sensorHumedadModel) {
		sensorHumedadModel.setActivo(false);
		sensorHumedadService.insertOrUpdate(sensorHumedadModel);
	    return "redirect:";
	}
}
