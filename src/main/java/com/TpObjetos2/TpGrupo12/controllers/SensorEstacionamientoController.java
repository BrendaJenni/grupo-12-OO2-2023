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
import com.TpObjetos2.TpGrupo12.models.MedicionEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionModel;
import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.models.SensorEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.IEventoService;
import com.TpObjetos2.TpGrupo12.services.IMedicionService;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;
import com.TpObjetos2.TpGrupo12.entities.*;

@Controller
@RequestMapping("/dispositivo/estacionamiento")
public class SensorEstacionamientoController {
	
	@Autowired
    @Qualifier("estacionamientoService")
    private ISensorEstacionamientoService estacionamientoService;
	
	@Autowired
    @Qualifier("medicionService")
	private IMedicionService medicionService;
	
	@Autowired
	@Qualifier("eventoService")
	private IEventoService eventoService;

    @GetMapping("")
    public String indexEstacionamiento(Model model){
    	List<SensorEstacionamiento> estacionamientos = estacionamientoService.traerstacionamientosActivos();
    	List<MedicionEstacionamiento> mediciones = medicionService.traer();
    	List<Evento> eventos = eventoService.getAll();
    	
       model.addAttribute("estacionamientos", estacionamientos);
       model.addAttribute("mediciones", mediciones);
       model.addAttribute("eventos", eventos);
    	
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
    	estacionamientoModel.setPlazas(estacionamientoService.actualizarPlazas(estacionamientoModel));
        estacionamientoService.insertOrUpdate(estacionamientoModel);
        return new RedirectView("/dispositivo/estacionamiento");
    }
    /*
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/agregarmedicion")
    public ModelAndView agreaarMediciones() {
    ModelAndView mAV = new ModelAndView("estacionamiento/agregarmedicion");
   mAV.addObject("mediciones", medicionService.traer());
    mAV.addObject("medicion", new MedicionEstacionamiento());
    return mAV;}*/
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/agregarmedicion")
    public ModelAndView agregarMedicion(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregarmedicion");
       MedicionEstacionamiento estacionamiento = new MedicionEstacionamiento();
       
       mAV.addObject("medicion", estacionamiento);
       return mAV;
    }
    
    /*@PostMapping("/agregarmedicion")
    public RedirectView create(@ModelAttribute("medicion") MedicionEstacionamientoModel estacionamientoModel){
        medicionService.insertOrUpdate(estacionamientoModel);
        return new RedirectView("/dispositivo/estacionamiento");
    }*/
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/agregarmedicion")
	   public String agregarMedicion(@RequestParam("dispositivoId") int dispositivoId,
	                                 @RequestParam("fecha") LocalDateTime fecha,
	                                 @RequestParam("estadoLibre") boolean estadoLibre){
	       Dispositivo dispositivo = estacionamientoService.findByid(dispositivoId); 
	       if (dispositivo != null) {      
	           //estacionamientoService.agregarMedicion(dispositivo,fecha,estadoLibre);
	    	   MedicionEstacionamientoModel medicion = new MedicionEstacionamientoModel(dispositivo, fecha, estadoLibre);
	    	   medicionService.insertOrUpdate(medicion);
	       }
	       
	       return "redirect:/dispositivo/estacionamiento";
	   }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/agregarevento")
    public ModelAndView agregarEvento(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregarevento");
       Evento evento = new Evento();
       
       mAV.addObject("evento", evento);
       return mAV;
    }
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/agregarevento")
	   public String agregarEvento(@RequestParam("dispositivoId") int dispositivoId,
	                                 @RequestParam("fecha") LocalDateTime fecha,
	                                 @RequestParam("descripcion") String descripcion){
	       Dispositivo dispositivo = estacionamientoService.findByid(dispositivoId); 
	       if (dispositivo != null) {      
	           estacionamientoService.agregarEventos(dispositivo, descripcion, fecha);
	       }
	       return "redirect:/dispositivo/estacionamiento";
	   }
    
}
