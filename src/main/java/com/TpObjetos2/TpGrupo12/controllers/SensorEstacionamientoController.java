package com.TpObjetos2.TpGrupo12.controllers;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.entities.MedicionEstacionamiento;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.models.SensorEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;
import com.TpObjetos2.TpGrupo12.services.IMedicionService;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;


@Controller
@RequestMapping("dispositivo/estacionamiento")
public class SensorEstacionamientoController {
	@Autowired
    @Qualifier("estacionamientoService")
    private ISensorEstacionamientoService estacionamientoService;
	
	@Autowired
    @Qualifier("medicionService")
	private IMedicionService medicionService;

    @GetMapping("")
    public String indexEstacionamiento(Model model){
    	List<SensorEstacionamiento> estacionamientos = estacionamientoService.traerstacionamientosActivos(); 
       model.addAttribute("estacionamientos", estacionamientos);
    	
       return "estacionamiento/estacionamiento";
    }
    
   
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public ModelAndView agregarEstacionamiento(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregar");
       SensorEstacionamiento estacionamiento = estacionamientoService.crearEstacionamientoConPlazas();
       mAV.addObject("boolean", estacionamiento.getPlazas());
       mAV.addObject("estacionamiento", estacionamiento);
       return mAV;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/bajaLogica")
    public String bajaLogica(@RequestParam("id") int id, SensorEstacionamientoModel sensorEstacionamientoModel) {
        Dispositivo dispositivo = estacionamientoService.findByid(id);
        dispositivo.setActivo(false);  
        
        estacionamientoService.insertOrUpdateEst(dispositivo);

        return "redirect:/dispositivo/estacionamiento";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/new")
    public RedirectView create(@ModelAttribute("estacionamiento") SensorEstacionamientoModel estacionamientoModel){
    	estacionamientoModel.inicializarPlazas();
        estacionamientoService.insertOrUpdate(estacionamientoModel);
        return new RedirectView("/dispositivo/estacionamiento");
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/agregarmedicion")
    public ModelAndView agreaarMediciones() {
    ModelAndView mAV = new ModelAndView("estacionamiento/agregarmedicion");
   mAV.addObject("mediciones", medicionService.getAll());
    mAV.addObject("medicion", new Medicion());
    return mAV;}
 
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/agregarmedicion")
	   public String agregarMedicion(@RequestParam("dispositivoId") int dispositivoId,
	                                 @RequestParam("fecha") LocalDateTime fecha,
	                                 @RequestParam("estadoLibre") boolean estadoLibre){
	       Dispositivo dispositivo = estacionamientoService.findByid(dispositivoId); 
	       if (dispositivo != null) {      
	           estacionamientoService.agregarMedicion(dispositivo,fecha,estadoLibre);
	       }
	       
	       return "redirect:/dispositivo/estacionamiento";
	   }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/agregarevento")
	   public String agregarEvento(@RequestParam("dispositivoId") int dispositivoId,
	                                 @RequestParam("fecha") LocalDateTime fecha,
	                                 @RequestParam("estadoLibre") boolean estadoLibre){
	       Dispositivo dispositivo = estacionamientoService.findByid(dispositivoId); 
	       if (dispositivo != null) {      
	           estacionamientoService.agregarMedicion(dispositivo,fecha,estadoLibre);
	       }
	       
	       return "redirect:/dispositivo/estacionamiento";
	   }
    
}
