package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.services.IEventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	@Autowired
    @Qualifier("eventoService")
    private IEventoService eventoService;
	
	@GetMapping("/")
    public ModelAndView index(){
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.EVENTO_INDEX);
        mAV.addObject("eventos",eventoService.getAll());
        mAV.addObject("evento", new Evento());
        return mAV;
    }
	
	 @PostMapping("/")
	    public RedirectView create(@ModelAttribute("evento") EventoModel eventoModel){
	        eventoService.insertOrUpdate(eventoModel);
	        return new RedirectView(ViewRouteHelper.EVENTO_ROOT);
	    }

}
