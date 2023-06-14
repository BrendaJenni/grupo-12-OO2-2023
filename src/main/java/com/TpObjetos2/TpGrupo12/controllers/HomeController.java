package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("/home")
	public ModelAndView index() {
		ModelAndView vista = new ModelAndView(ViewRouteHelper.INDEX);
		return vista;
	}
	
	@GetMapping("/")
    public RedirectView redirectToHomeIndex(){
        return new RedirectView(ViewRouteHelper.ROUTE_INDEX);
    }
}
