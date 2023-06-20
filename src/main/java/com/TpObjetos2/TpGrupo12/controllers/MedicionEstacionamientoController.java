package com.TpObjetos2.TpGrupo12.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;

@Controller
//@RequestMapping("/estacionamiento")
public class MedicionEstacionamientoController {
	/*
	@Autowired
    @Qualifier("estacionamientoService")
    private ISensorAlumbradoService sensorAlumbradoService;
	
	@GetMapping("/alumbrado")
    public String index(Model model){
		List<SensorAlumbrado> dispositivos = sensorAlumbradoService.getAll();
		List<SensorAlumbrado> dispositivosAlumbrado = new ArrayList<>();
		for (SensorAlumbrado dispositivo : dispositivos) {
			
			if (dispositivo.isActivo() == true) {
				
				dispositivosAlumbrado.add(dispositivo);
			}
		}
		model.addAttribute("dispositivos", dispositivosAlumbrado);
       return "dispositivo/alumbrado";
    }
	
	@PostMapping("/dispositivo/alumbrado")
    public String createAlum(@ModelAttribute("dispositivo") SensorAlumbradoModel sensorAlumbradoModel) {
        sensorAlumbradoService.insertOrUpdate(sensorAlumbradoModel);
        return "redirect:/sensoralumbrado/alumbrado"; // Redirige al listado de sensoresalumbrado
    }	
	
	   @PostMapping("/bajaLogica")
	    public String bajaLogica(@RequestParam("id") int id, SensorAlumbradoModel sensorAlumbradoModel) {
	        Dispositivo dispositivo = sensorAlumbradoService.findByid(id);
	        dispositivo.setActivo(false);  
	        
	        sensorAlumbradoService.insertOrUpdatealum(dispositivo);

	        return "redirect:/sensoralumbrado/alumbrado";
	    }
	  
	   @PostMapping("/agregarMedicion")
	   public String agregarMedicion(@RequestParam("dispositivoId") int dispositivoId,
	                                 @RequestParam("fecha") LocalDateTime fecha,
	                                 @RequestParam("estadoActual") boolean estadoActual,
	                                 @RequestParam("obscuridadActualPor") double obscuridadActualPor) {
	       Dispositivo dispositivo = sensorAlumbradoService.findByid(dispositivoId); 
	       if (dispositivo != null) {      
	           sensorAlumbradoService.agregarMedicion(dispositivo,fecha,estadoActual,obscuridadActualPor);
	       }
	       
	       return "redirect:/sensoralumbrado/alumbrado";
	   }*/
	   

	
}
