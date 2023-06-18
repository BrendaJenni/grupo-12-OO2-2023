package com.TpObjetos2.TpGrupo12.controllers;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
    
}
