package com.TpObjetos2.TpGrupo12.controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.TpObjetos2.TpGrupo12.services.IEventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	@Autowired
    @Qualifier("eventoService")
    private IEventoService eventoService;
	
	@GetMapping("/") //mi index
    public String index(Model model){
       model.addAttribute("eventos", eventoService.getAll());
       return "evento/evento";
    }
	
	@GetMapping("/busquedafecha") 
    public String buscarFecha(@RequestParam("fecha")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime fecha,Model model){
       if(fecha != null) {
    	   model.addAttribute("eventos",eventoService.getByFecha(fecha));
       }
       return "evento/evento";
    }
	@GetMapping("/busquedaestado") 
    public String buscarEstado(@RequestParam("estado")boolean estado,Model model){
       if(String.valueOf(estado) != "") {
    	   model.addAttribute("eventos",eventoService.getByEstado(estado));
       }
       return "evento/evento";
    }
	@GetMapping("/busquedaid") 
    public String buscarid(@RequestParam("id")int id,Model model){
        if(String.valueOf(id) != "") {
     	   model.addAttribute("eventos",eventoService.getByIdDispositivo(id));
        }
        return "evento/evento";
     }
}
