package com.TpObjetos2.TpGrupo12.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

@Controller
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    @Qualifier("dispositivoService")
    private IDispositivoService dispositivoService;

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.DISPOSITIVO_INDEX);
        mAV.addObject("dispositivos", dispositivoService.getAll());
        mAV.addObject("dispositivo", new Dispositivo());
        return mAV;
    }

    @PostMapping("/")
    public RedirectView create(@ModelAttribute("dispositivo") DispositivoModel dispositivoModel){
        dispositivoService.insertOrUpdate(dispositivoModel);
        
        return new RedirectView(ViewRouteHelper.DISPOSITIVO_ROOT);
    }
    
//    @Autowired
//    private IDispositivoRepository dispositivoRepository;
//    
//    @PostMapping("/dispositivos")
//    public String guardarDispositivo(@ModelAttribute Dispositivo dispositivo) {
//        if (dispositivo instanceof RecolectorInteligente) {
//            RecolectorInteligente recolector = (RecolectorInteligente) dispositivo;
//            // Acceder a los atributos específicos de RecolectorInteligente
//            String ubicacion = recolector.getUbicacion();
//            // ...
//            dispositivoRepository.save(recolector); // Guardar en la tabla de RecolectorInteligente
//        } else if (dispositivo instanceof SensorHumedad) {
//            SensorHumedad sensorHumedad = (SensorHumedad) dispositivo;
//            // Acceder a los atributos específicos de SensorHumedad
//            boolean encendido = sensorHumedad.isEncedido();
//            // ...
//            dispositivoRepository.save(sensorHumedad); // Guardar en la tabla de SensorHumedad
//        } else if (dispositivo instanceof SensorAlumbrado) {
//        	SensorAlumbrado sensorAlumbrado = (SensorAlumbrado) dispositivo;
//            // Acceder a los atributos específicos de SensorHumedad
//            String estacion = sensorAlumbrado.getEstacion();
//            boolean endendido = sensorAlumbrado.isEncendido();
//            double obcuridadPor = sensorAlumbrado.getObscuridadPor();
//            // ...
//            dispositivoRepository.save(sensorAlumbrado);
//        } else if (dispositivo instanceof SensorEstacionamiento) {
//        	SensorEstacionamiento sensorEstacionamiento = (SensorEstacionamiento) dispositivo;
//            // Acceder a los atributos específicos de SensorHumedad
//            boolean endendido = sensorEstacionamiento.isPlazas();
//            // ...
//            dispositivoRepository.save(sensorEstacionamiento);
//
//        // Guardar en la tabla de Dispositivo (clase padre)
//        dispositivoRepository.save(dispositivo);
//        
//        return "redirect:/dispositivos";
//        }
//    }
}
