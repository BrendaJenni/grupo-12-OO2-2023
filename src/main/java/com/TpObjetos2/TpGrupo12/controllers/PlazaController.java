package com.TpObjetos2.TpGrupo12.controllers;

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
/*
import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Plaza;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.PlazaModel;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.IPlazaService;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/estacionamiento/plazas", method = RequestMethod.GET)
public class PlazaController {
	@Autowired
    @Qualifier("plazaService")
    private IPlazaService plazaService;
	
	//@Autowired
    //@Qualifier("dispositivoService")
    //private IDispositivoService dispositivoService;

    @GetMapping("/")
    public String indexPlazas(Model model){
       model.addAttribute("plazas", plazaService.getAll());
       return "estacionamiento/plazas";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public ModelAndView agregarEstacionamiento(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregarplazas");
       //mAV.addObject("plazas", plazaService.getAll());
       mAV.addObject("plaza", new Plaza());
       return mAV;
    }
    
    @GetMapping("/new")
    public ModelAndView index(){
       ModelAndView mAV = new ModelAndView("estacionamiento/agregarplazas");
       //mAV.addObject("plazas", plazaService.getAll());
       mAV.addObject("plaza", new Plaza());
       return mAV;
    }
    
    
    @PostMapping("/new")
    public RedirectView create(@ModelAttribute("plaza") PlazaModel plazaModel){
        plazaService.insertOrUpdate(plazaModel);
        return new RedirectView("/dispositivo/");
    }
    
}*/