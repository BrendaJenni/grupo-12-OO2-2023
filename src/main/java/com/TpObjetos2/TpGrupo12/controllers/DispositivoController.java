package com.TpObjetos2.TpGrupo12.controllers;

import java.util.*;


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
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;
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

    
    public DispositivoController(IDispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

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

    /*@PostMapping("new")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
    	sensorHumedadService.insertOrUpdate(dispositivoModel);
    	return new RedirectView("");
    }*/

    //agrego aca esta funcion porque desde dispositivo puedo ingresar a todos los datos por lo que es importante que pueda quedarme aqui
    //este mapping manda todos los datos al front ingresado en el return , esos datos pueden ser consumidos desde dispositivos desde el html para mostrar todo lo que queremos
    //en caso de agregar en otra clase debemos modificar el getmapping y ademas el return para que devuelva la info para la url correspondiente
    @GetMapping("/alumbrado")
    public String indexAlumbrado(Model model) {
        List<Dispositivo> dispositivos = dispositivoService.getAll();
        List<Dispositivo> dispositivosAlumbrado = new ArrayList<>();

        for (Dispositivo dispositivo : dispositivos) {
            if (dispositivo instanceof SensorAlumbrado) {
                List<Medicion> mediciones = dispositivo.getMediciones();
                List<Medicion> medicionesAlumbrado = new ArrayList<>();

                for (Medicion medicion : mediciones) {
                    if (medicion instanceof MedicionAlumbrado) {
                        medicionesAlumbrado.add(medicion);
                    }
                }

                dispositivo.setMediciones(medicionesAlumbrado);
                dispositivosAlumbrado.add(dispositivo);
            }
        }

        model.addAttribute("dispositivos", dispositivosAlumbrado);

        return "dispositivo/alumbrado";
    }
    
    @PostMapping("/dispositivo/bajaLogica")
	public String eliminarSensor(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel) {
		DispositivoModel dispositivoModel1 = new DispositivoModel(0, null, false);
		dispositivoModel1.setActivo(false); // Establecer el campo "activo" en false
	    dispositivoService.insertOrUpdate(dispositivoModel1);
	    return "redirect:/dispositivo/alumbrado";
	}

    
}
