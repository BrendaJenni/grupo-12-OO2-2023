package com.TpObjetos2.TpGrupo12.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/dispositivo/";
	}
	
	/*@PostMapping("/loginsuccess")
	public boolean esAdmin(@RequestParam("username") String username, Model model) {
	    // Lógica para buscar al usuario en la base de datos y obtener sus roles
	    User user = userService.findByUsernameAndFetchUserRolesEagerly(username);
	    
	    // Verificamos si el usuario tiene el rol "ROLE_ADMIN"
	    boolean isAdmin = false;
	    for (UserRoles userRole : user.getUserRoles()) {
	        if (userRole.getRole().equals("ROLE_ADMIN")) {
	            isAdmin = true;
	            break;
	        }
	    }

	    return isAdmin;
	}*/
}