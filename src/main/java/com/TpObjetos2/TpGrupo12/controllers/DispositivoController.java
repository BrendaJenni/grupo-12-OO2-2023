package com.TpObjetos2.TpGrupo12.controllers;

import org.modelmapper.ModelMapper;
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

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;

@Controller
@RequestMapping("/")
public class DispositivoController {
    @Autowired
    @Qualifier("dispositivoService")
    private IDispositivoService dispositivoService;

    @GetMapping("")
    public String index(Model model){
       model.addAttribute("dispositivos", dispositivoService.getAll());
       return "dispositivo/index";
    }

    @GetMapping("new")
    public ModelAndView index(){
       ModelAndView mAV = new ModelAndView("dispositivo/new");
       mAV.addObject("dispositivos", dispositivoService.getAll());
       mAV.addObject("dispositivo", new Dispositivo());
       return mAV;
    }

    @PostMapping("new")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
        dispositivoService.insertOrUpdate(dispositivoModel);
        return new RedirectView("dispositivo/new");
    }
    
    
}
