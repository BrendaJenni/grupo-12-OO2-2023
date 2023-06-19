package com.TpObjetos2.TpGrupo12.controllers;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
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
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorEstacionamiento;
import com.TpObjetos2.TpGrupo12.entities.SensorHumedad;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.IPlazaService;
import com.TpObjetos2.TpGrupo12.entities.*;

import jakarta.servlet.http.HttpServletRequest;

import com.TpObjetos2.TpGrupo12.models.SensorHumedadModel;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.ISensorHumedadService;

@Controller
@RequestMapping("/")
public class DispositivoController {
    @Autowired
    @Qualifier("dispositivoService")
    private IDispositivoService dispositivoService;
    @Autowired
    @Qualifier("sensorHumedadService")
    private ISensorHumedadService sensorHumedadService;
    

    @GetMapping("")
    public String index(Model model){
       model.addAttribute("dispositivos", dispositivoService.getAll());
       return "dispositivo/index";
    }

    @GetMapping("new")
    public ModelAndView index(){
       ModelAndView mAV = new ModelAndView("dispositivo/new");
       mAV.addObject("dispositivos", dispositivoService.getAll());
       mAV.addObject("dispositivo", new DispositivoModel());
       return mAV;
    }

    @PostMapping("new")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
    	sensorHumedadService.insertOrUpdate(dispositivoModel);
    	return new RedirectView("");
    }
    
    /*@SuppressWarnings("unused")
	@PostMapping("new")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
    	if(new SensorHumedad(dispositivoModel.getId(),dispositivoModel.getNombre(),dispositivoModel.isActivo(),dispositivoModel.isEncendido()) != null) {
    		sensorHumedadService.insertOrUpdate(new SensorHumedadModel(dispositivoModel.getId(),dispositivoModel.getNombre(),dispositivoModel.isActivo(),dispositivoModel.isEncendido()));
    	}else {
    		dispositivoService.insertOrUpdate(dispositivoModel);
    	}
        
        return new RedirectView("");
    }*/
}