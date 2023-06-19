package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Evento;
import com.TpObjetos2.TpGrupo12.entities.MedicionRecolector;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.EventoModel;
import com.TpObjetos2.TpGrupo12.models.MedicionModel;
import com.TpObjetos2.TpGrupo12.services.IEventoService;
import com.TpObjetos2.TpGrupo12.services.IMedicionService;

@Controller
@RequestMapping("/medicion")
public class MedicionController {
	
	@Autowired
    @Qualifier("medicionService")
    private IMedicionService medicionService;
	
	@GetMapping("/")
    public ModelAndView index(){
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.MEDICION_INDEX);
        mAV.addObject("mediciones",medicionService.getAll());
        mAV.addObject("medicion", new Evento());
        return mAV;
    }
	
	 @PostMapping("/")
	    public RedirectView create(@ModelAttribute("medicion") MedicionModel medicionModel){
	        medicionService.insertOrUpdate(medicionModel);
	        return new RedirectView(ViewRouteHelper.MEDICION_ROOT);
	    }

	 @RequestMapping(value = "/chequear", method = RequestMethod.GET)
	    public String chequearContenidoRecolector(MedicionRecolector mRecolector) {
		 	chequearContenidoRecolector(mRecolector);

	        return "/medicion/";
	    }
}
