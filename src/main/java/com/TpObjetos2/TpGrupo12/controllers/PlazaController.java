package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Plaza;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.models.PlazaModel;
import com.TpObjetos2.TpGrupo12.services.IPlazaService;

@Controller
@RequestMapping("/dispositivo/estacionamiento")
public class PlazaController {
	@Autowired
    @Qualifier("plazaService")
    private IPlazaService plazaService;
	
	@GetMapping("/")
    public String index(Model model){
       model.addAttribute("plazas", plazaService.getAll());
       return "dispositivo/estacionamiento";
    }
	
	@GetMapping("/plazas")
    public ModelAndView sensorEstacionamiento() {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelper.DISPOSITIVOESTACIONAMIENTO_INDEX);
        mAV.addObject("plazas", plazaService.getAll());
        mAV.addObject("plaza", new Plaza());
        return mAV;
    }
	
}
