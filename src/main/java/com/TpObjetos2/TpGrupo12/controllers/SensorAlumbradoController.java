package com.TpObjetos2.TpGrupo12.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.TpObjetos2.TpGrupo12.models.SensorAlumbradoModel;
import com.TpObjetos2.TpGrupo12.services.ISensorAlumbradoService;

@Controller
@RequestMapping("/sensoralumbrado")
public class SensorAlumbradoController {
	
	@Autowired
    @Qualifier("sensorAlumbradoService")
    private ISensorAlumbradoService sensorAlumbradoService;
	
	@GetMapping("/")
    public String index(Model model){
       model.addAttribute("sensor", sensorAlumbradoService.getAll());
       return "dispositivo/alumbrado";
    }
	
	@PostMapping("/dispositivo/alumbrado")
    public String createAlum(@ModelAttribute("dispositivo") SensorAlumbradoModel sensorAlumbradoModel) {
        sensorAlumbradoService.insertOrUpdate(sensorAlumbradoModel);
        return "redirect:/dispositivo/alumbrado"; // Redirige al listado de sensoresalumbrado
    }
	
	

}
