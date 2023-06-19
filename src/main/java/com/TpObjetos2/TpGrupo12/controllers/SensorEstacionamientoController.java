package com.TpObjetos2.TpGrupo12.controllers;

import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.models.SensorEstacionamientoModel;
import com.TpObjetos2.TpGrupo12.repositories.ISensorEstacionamientoRepository;
import com.TpObjetos2.TpGrupo12.services.ISensorEstacionamientoService;


@Controller
@RequestMapping("/estacionamiento")
public class SensorEstacionamientoController {
	//@Autowired
    //@Qualifier("plazaService")
    //private IPlazaService plazaService;
	
	@Autowired
    @Qualifier("estacionamientoService")
    private ISensorEstacionamientoService estacionamientoService;
	
	@Autowired
	private ISensorEstacionamientoRepository estacionamientoRepository;

    @GetMapping("/")
    public String indexEstacionamiento(Model model){
       model.addAttribute("estacionamientos", estacionamientoService.getAll());
       return "estacionamiento/estacionamiento";
    }
    
    @GetMapping("/plazas")
    public String indexPlazas(Model model){
       //model.addAttribute("estacionamiento", estacionamientoService.getAll());
       model.addAttribute("estacionamiento.plazas", estacionamientoService.getPlazas());
       return "estacionamiento/plazas";
    }
    /*
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/plazas/new")
    public ModelAndView agregarEstacionamientoPlazas(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregarplazas");
       //mAV.addObject("plazas", plazaService.getAll());
       mAV.addObject("plaza", new Plaza());
       return mAV;
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/plazas/new")
    public String agregarPlazas(Model model) {
    	List<SensorEstacionamiento> estacionamientos=estacionamientoRepository.findAll();
    	model.addAttribute("plaza", new Plaza());
    	model.addAttribute("estacionamientos", estacionamientos);
    	return("estacionamiento/agregarplazas");
    }
    
    @GetMapping("/plazas")
    public String indexPlazas(Model model){
       model.addAttribute("plazas", plazaService.getAll());
       return "estacionamiento/plazas";
    }*/
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public ModelAndView agregarEstacionamiento(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregar");
       //mAV.addObject("plazas", plazaService.getAll());
       SensorEstacionamiento estacionamiento = new SensorEstacionamiento();
       List<Boolean> plazas = new ArrayList<Boolean>();
       estacionamiento.setPlazas(plazas);
       estacionamiento.inicializarPlazas();
       mAV.addObject("boolean", estacionamiento.getPlazas());
       mAV.addObject("estacionamiento", estacionamiento);
       //mAV.addObject("boolean", new ArrayList<Boolean>());
       return mAV;
    }
    
    
    @PostMapping("/new")
    public RedirectView create(@ModelAttribute("estacionamiento") SensorEstacionamientoModel estacionamientoModel){
    	estacionamientoModel.inicializarPlazas();
        estacionamientoService.insertOrUpdate(estacionamientoModel);
        return new RedirectView("/estacionamiento/");
    }
    /*
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/plazas/new")
    public ModelAndView agregarPlazas(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregarplazas");
       //mAV.addObject("plazas", plazaService.getAll());
       SensorEstacionamiento estacionamiento = estacionamientoService.findByid(0);
       List<Boolean> plazas = new ArrayList<Boolean>();
       mAV.addObject("estacionamiento", new SensorEstacionamiento());
       mAV.addObject("boolean", estacionamiento.getPlazas());
       return mAV;
    }*/
    
    @PostMapping("/plazas/new")
    public RedirectView createPlazas(@ModelAttribute("estacionamiento") SensorEstacionamientoModel estacionamientoModel){
        estacionamientoService.insertOrUpdate(estacionamientoModel);
        return new RedirectView("/dispositivo/");
    }
    
    /*
    @PostMapping("/plazas/new")
    public RedirectView create(@ModelAttribute("plaza") PlazaModel plazaModel){
        plazaService.insertOrUpdate(plazaModel);
        return new RedirectView("/dispositivo/");
    }*/
    
}
