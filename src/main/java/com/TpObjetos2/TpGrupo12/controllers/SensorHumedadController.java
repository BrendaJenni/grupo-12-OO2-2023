package com.TpObjetos2.TpGrupo12.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;
import com.TpObjetos2.TpGrupo12.services.IEventoService;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;

@Controller
@RequestMapping("/sensorhumedad")
public class SensorHumedadController {

	@Autowired
    @Qualifier("sensorHumedadService")
    private ISensorHumedadService sensorHumedadService;
	private ModelMapper modelMapper = new ModelMapper();

	@GetMapping("/") //mi index
    public String index(Model model){
		List<SensorHumedad> dispositivos = sensorHumedadService.getAll();
		List<SensorHumedad> dispositivosHumedad = new ArrayList<>();
		for (SensorHumedad dispositivo : dispositivos) {
			if (dispositivo.isActivo() == true) {
				dispositivosHumedad.add(dispositivo);
			}
		}
       model.addAttribute("dispositivos", dispositivosHumedad);
       return "dispositivo/humedad";
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("crearsensor")
    public String create(@ModelAttribute("dispositivo") SensorHumedadModel sensorHumedadModel) {
		sensorHumedadService.insertOrUpdate(modelMapper.map(sensorHumedadModel, SensorHumedad.class));
        return "redirect:";
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/bajaLogica")
    public String bajaLogica(@RequestParam("id") int id) {
		Dispositivo dispositivo = sensorHumedadService.findByid(id);
        dispositivo.setActivo(false);
        sensorHumedadService.actualizar(dispositivo);

        return "redirect:";
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/agregarmedicion")
	   public String agregarMedicion(@RequestParam("dispositivoId") int dispositivoId,
	                                 @RequestParam("fecha") LocalDateTime fecha,
	                                 @RequestParam("humedad") int humedad,
	                                 @RequestParam("estadoCesped") boolean estadoCesped) {
	       Dispositivo dispositivo = sensorHumedadService.findByid(dispositivoId); 
	       if (dispositivo != null) {      
	           sensorHumedadService.agregarMedicion(dispositivo,fecha,humedad, estadoCesped);
	       }
	       
	       return "redirect:/sensorhumedad/";
	   }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/agregarevento") 
    public String buscar(@RequestParam("fecha")LocalDateTime fecha,
    						@RequestParam("descripcion")String descripcion,
    						@RequestParam("dispositivoId")int dispositivoId){
       Dispositivo dispositivo = sensorHumedadService.findByid(dispositivoId);
       Evento evento = new Evento();
       evento.setFechaRegistro(fecha);
       evento.setDescripcion(descripcion);
       evento.setDispositivo(dispositivo);
       if (dispositivo != null) {      
           sensorHumedadService.agregarEventos(dispositivo,evento);
       }
       return "redirect:/evento/";
    }

}
