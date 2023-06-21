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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.models.SensorRecolectorModel;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.IRecolectorInteligenteService;
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;

@Controller
@RequestMapping("/recolectorinteligente")
public class RecolectorInteligenteController {
	@Autowired
    @Qualifier("recolectorInteligenteService")
    private IRecolectorInteligenteService sensorRecolectorService;
	
	@GetMapping("/") //mi index
	public String index(Model model){
	   model.addAttribute("dispositivos", sensorRecolectorService.getAll());
	   return "dispositivo/recolector";
	}

	@PostMapping("createSensorRecolector") //inserta
	public RedirectView create(@ModelAttribute("dispositivo") SensorRecolectorModel sensorRecolectorModel){
		sensorRecolectorService.insertOrUpdate(sensorRecolectorModel);
	    return new RedirectView("/recolectorinteligente/");
	}

	@PostMapping("/bajaLogica")
	public String eliminarSensor(@ModelAttribute("dispositivo") SensorRecolectorModel sensorRecolectorModel) {
	   sensorRecolectorModel.setActivo(false);
	   sensorRecolectorService.insertOrUpdate(sensorRecolectorModel);
	   return "redirect:/recolectorinteligente/";
	}
	
	@PostMapping("/agregarevento") 
    public String buscar(@RequestParam("fecha")LocalDateTime fecha,
    						@RequestParam("descripcion")String descripcion,
    						@RequestParam("dispositivoId")int dispositivoId){
       Dispositivo dispositivo = sensorRecolectorService.findByid(dispositivoId);
       Evento evento = new Evento();
       evento.setFechaRegistro(fecha);
       evento.setDescripcion(descripcion);
       evento.setDispositivo(dispositivo);
       if (dispositivo != null) {      
    	   sensorRecolectorService.agregarEventos(dispositivo,evento);
       }
       return "redirect:/evento/";
    }
	
}