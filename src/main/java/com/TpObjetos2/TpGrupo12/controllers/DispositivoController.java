package com.TpObjetos2.TpGrupo12.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.TpObjetos2.TpGrupo12.entities.Dispositivo;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;

@Controller
@RequestMapping("/dispositivo")
public class DispositivoController {

	@Autowired
	@Qualifier("personService")
	private IDispositivoService personService;

	private ModelMapper modelMapper = new ModelMapper();
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DISPOSITIVO_INDEX);
		mAV.addObject("persons", personService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DISPOSITIVO_NEW);
		mAV.addObject("dispositivo", new Dispositivo());
		return mAV;
	}

	/*@GetMapping("")
	public ModelAndView index() {
		ModelAndView vista = new ModelAndView("home/index");
		return vista;
	}*/
}
