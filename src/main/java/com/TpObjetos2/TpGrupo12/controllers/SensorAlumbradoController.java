package com.TpObjetos2.TpGrupo12.controllers;


import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;

@Controller
@RequestMapping("/sensoralumbrado")
public class SensorAlumbradoController {
	
	@Autowired
    @Qualifier("sensorAlumbradoService")
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/dispositivo/alumbrado")
    public String createAlum(@ModelAttribute("dispositivo") SensorAlumbradoModel sensorAlumbradoModel) {
        sensorAlumbradoService.insertOrUpdate(sensorAlumbradoModel);
        return "redirect:/sensoralumbrado/alumbrado"; // Redirige al listado de sensoresalumbrado
    }	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	   @PostMapping("/bajaLogica")
	    public String bajaLogica(@RequestParam("id") int id, SensorAlumbradoModel sensorAlumbradoModel) {
	        Dispositivo dispositivo = sensorAlumbradoService.findByid(id);
	        
	        sensorAlumbradoService.insertOrUpdatealum(dispositivo);

	        return "redirect:/sensoralumbrado/alumbrado";
	    }
	  
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	   }
	   
	   
	@PreAuthorize("hasRole('ROLE_ADMIN')")
		@PostMapping("/agregarevento") 
	    public String buscar(@RequestParam("fecha")LocalDateTime fecha,
	    						@RequestParam("descripcion")String descripcion,
	    						@RequestParam("dispositivoId")int dispositivoId){
	       Dispositivo dispositivo = sensorAlumbradoService.findByid(dispositivoId);
	       Evento evento = new Evento();
	       evento.setFechaRegistro(fecha);
	       evento.setDescripcion(descripcion);
	       evento.setDispositivo(dispositivo);
	       if (dispositivo != null) {      
	    	   sensorAlumbradoService.agregarEventos(dispositivo,evento);
	       }
	       return "redirect:/evento/";
	    }
	   
}
