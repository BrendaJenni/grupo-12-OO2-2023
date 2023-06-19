package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.TpObjetos2.TpGrupo12.entities.User;
import com.TpObjetos2.TpGrupo12.entities.UserRoles;
import com.TpObjetos2.TpGrupo12.helpers.ViewRouteHelper;
import com.TpObjetos2.TpGrupo12.repositories.IUserRepository;
import com.TpObjetos2.TpGrupo12.services.IDispositivoService;




@Controller
public class UserController {
	@Autowired
    @Qualifier("userRepository")
    private IUserRepository userService;

	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout
						) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}
	
	//funciona login si es un role admin se dirige a una pagina , si es otro rol se dirije a otra url
	@GetMapping("/loginsuccess")
    public ModelAndView loginCheck(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            modelAndView.setViewName("redirect:/dispositivo/"); // esto sucede si es admin
        } else {
            modelAndView.setViewName("redirect:/dispositivo/alumbrado"); // Redirige a la URL deseada cuando no es admin
        }
        return modelAndView;
    }
}