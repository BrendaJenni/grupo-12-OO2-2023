package com.TpObjetos2.TpGrupo12.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;




import com.TpObjetos2.TpGrupo12.services.IDispositivoService;

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
       return "dispositivo/index";
    }


    
}
