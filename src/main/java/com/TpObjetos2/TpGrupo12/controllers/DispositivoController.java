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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.entities.Medicion;
import com.TpObjetos2.TpGrupo12.entities.MedicionAlumbrado;
import com.TpObjetos2.TpGrupo12.entities.SensorAlumbrado;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.models.DispositivoModel;

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

    @PostMapping("/")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
        dispositivoService.insertOrUpdate(dispositivoModel);
        return new RedirectView(ViewRouteHelper.DISPOSITIVO_ROOT);
    }
    
    
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

                //agregamos este if para mostrar solo los dispositivos que estan en true
                //al gestionar la baja logica tenemos que dejar de mostrarlos
               if (dispositivo.isActivo() == true) {
                dispositivo.setMediciones(medicionesAlumbrado);
                dispositivosAlumbrado.add(dispositivo);
                }            }
        }

        model.addAttribute("dispositivos", dispositivosAlumbrado);

        return "dispositivo/alumbrado";
    }
    
    @PostMapping("/bajaLogica")
    public String bajaLogica(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        Dispositivo dispositivo = dispositivoService.findByid(id);
        dispositivo.setActivo(false);
        dispositivoService.insertOrUpdatealum(dispositivo);

        return "redirect:/dispositivo/alumbrado";
    }
    
}
