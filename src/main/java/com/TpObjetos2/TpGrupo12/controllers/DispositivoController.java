package com.TpObjetos2.TpGrupo12.controllers;

import java.util.*;

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
import com.TpObjetos2.TpGrupo12.entities.RecolectorInteligente;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;

import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.entities.*;

import jakarta.servlet.http.HttpServletRequest;

import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    @Qualifier("dispositivoService")
    private IDispositivoService dispositivoService;
    
    public DispositivoController(IDispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping("/")
    public String index(Model model){
       model.addAttribute("dispositivos", dispositivoService.getAll());
       return "dispositivo/index";
    }

    @PostMapping("/")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
        dispositivoService.insertOrUpdate(dispositivoModel);
        return new RedirectView(ViewRouteHelper.DISPOSITIVO_ROOT);
    }
    
}
