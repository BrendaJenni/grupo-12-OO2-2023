package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoController {

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView vista = new ModelAndView("home/index");
		return vista;
	}
}
