package com.TpObjetos2.TpGrupo12.controllers;

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
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
import com.TpObjetos2.TpGrupo12.repositories.IDispositivoRepository;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;
import com.TpObjetos2.TpGrupo12.services.IPlazaService;
import com.TpObjetos2.TpGrupo12.entities.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    @Qualifier("dispositivoService")
    private IDispositivoService dispositivoService;
    

    /*@GetMapping("/")
    public ModelAndView index(){
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DISPOSITIVO_INDEX);
        mAV.addObject("dispositivo", dispositivoService.getAll());
        mAV.addObject("dispositivo", new Dispositivo());
        return mAV;
    }*/
    
    @GetMapping("/")
    public String index(Model model){
       model.addAttribute("dispositivos", dispositivoService.getAll());
       return "dispositivo/dispositivos";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public ModelAndView index(){
       ModelAndView mAV = new ModelAndView(ViewRouteHelper.DISPOSITIVO_INDEX);
       mAV.addObject("dispositivos", dispositivoService.getAll());
       mAV.addObject("dispositivo", new Dispositivo());
       return mAV;
    }
    /*
    @PostMapping("new")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
    	sensorHumedadService.insertOrUpdate(dispositivoModel);
    	return new RedirectView("");
    }
    
    
    /*
    @Autowired
    private IDispositivoRepository dispositivoRepository;
    
    @PostMapping("/submit")
    public String submitForm(@RequestParam("tipoDispositivo") String tipoDispositivo, 
                             @ModelAttribute("dispositivo") Dispositivo dispositivo, HttpServletRequest request) {
    	String nombre = request.getParameter("nombre");
    	Boolean activo = request.getParameter("activo") != null;
        // Obtener los parámetros específicos del tipo de dispositivo según el tipoDispositivo
        if (tipoDispositivo.equals("recolectorInteligente")) {
            String ubicacion = request.getParameter("ubicacion");
            RecolectorInteligente recolector = new RecolectorInteligente(nombre, activo, ubicacion);
            dispositivoRepository.save(recolector);
            // Realizar acciones específicas para Recolector Inteligente
        } else if (tipoDispositivo.equals("sensorHumedad")) {
            boolean encendido = request.getParameter("encendido") != null;
            SensorHumedad sensorHumedad = new SensorHumedad(nombre, activo, encendido);
            dispositivoRepository.save(sensorHumedad);
            // Realizar acciones específicas para Sensor de Humedad
        } else if (tipoDispositivo.equals("sensorAlumbrado")) {
            String estacion = request.getParameter("estacion");
            boolean encendido = request.getParameter("encendido") != null;
            double obcuridadPor = (double) Integer.parseInt(request.getParameter("plazas"));
        	SensorAlumbrado sensorAlumbrado = new SensorAlumbrado(nombre, activo, estacion, encendido, obcuridadPor);
        	dispositivoRepository.save(sensorAlumbrado);
            // Realizar acciones específicas para Sensor de Alumbrado
        } else if (tipoDispositivo.equals("sensorEstacionamiento")) {
            int plazas = Integer.parseInt(request.getParameter("plazas"));
        	SensorEstacionamiento sensorEstacionamiento = new SensorEstacionamiento(nombre, activo, plazas);
        	dispositivoRepository.save(sensorEstacionamiento);
            // Realizar acciones específicas para Sensor de Estacionamiento
        }
        
        return "redirect:/"; // Redirige a la página principal después de guardar
    }
    /*
    @GetMapping({"/dispositivos/", "/"})
    public String dispositivos(Model model) {
    	model.addAttribute("dispositivos", dispositivoService.getAll());
    	return "dispositivo/new";
    }*/

    @PostMapping("/new")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
        dispositivoService.insertOrUpdate(dispositivoModel);
        return new RedirectView("/dispositivo/");
    }
    
    //Dispositivo dispositivo = new Dispositivo();
    /*
    @GetMapping("/")
    public String index2(Model model) {
    	ModelAndView mv = new ModelAndView("dispositivo/dispositivos");
    	mv.addObject("dispositivo", dispositivo);
    	return mv;
    }*/
}
