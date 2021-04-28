package com.loli.demo.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login( @RequestParam(value="error", required = false)String error,
			@RequestParam(value="logout", required = false)String logout,Model model, Principal principal, RedirectAttributes flash) {
		if(principal != null) {
			flash.addFlashAttribute("info","Ya ha iniciado sesion");
			return "redirect:/carrito/home";
		}
		
		if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o ocntrasela incorrecta, por favor vuelva a intentarlo");
		}
		if(logout != null) {
			model.addAttribute("success","ha cerrado sesion con éxito");
		}
		return "login/login";
	}
}
