package com.TpObjetos2.TpGrupo12.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionModel;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.IEventoService;
import com.TpObjetos2.TpGrupo12.services.IMedicionService;

@Controller
@RequestMapping("/medicion")
public class MedicionController {
	
	@Autowired
    @Qualifier("medicionService")
    private IMedicionService medicionService;
	
	@Autowired
	@Qualifier("dispositivoService")
	private IDispositivoService dispositivoService;
	
	@GetMapping("/")
    public ModelAndView index(){
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.MEDICION_INDEX);
        mAV.addObject("mediciones",medicionService.getAll());
        mAV.addObject("medicion", new Medicion());
        return mAV;
    }
	
	@GetMapping("/new")
    public ModelAndView create(){
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.MEDICION_NEW);
        List<Dispositivo> dispositivos = dispositivoService.getAll();
        mAV.addObject("mediciones",medicionService.getAll());
        mAV.addObject("dispositivos", dispositivos);
        
        Dispositivo dispositivo = new Dispositivo();
        mAV.addObject("medicion", new Medicion());
        mAV.addObject("dispositivo_id", dispositivo.getId());
        return mAV;
    }
	
	 @PostMapping("/new")
	    public RedirectView create(@ModelAttribute("medicion") MedicionModel medicionModel){
	        medicionService.insertOrUpdate(medicionModel);
	        return new RedirectView("/medicion/");
	    }

}
