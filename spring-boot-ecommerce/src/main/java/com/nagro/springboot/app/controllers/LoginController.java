package com.nagro.springboot.app.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagro.springboot.app.model.entity.Administrador;
import com.nagro.springboot.app.model.service.IAdministradorService;

@Controller
@RequestMapping("/administracion")
@SessionAttributes("administrador")
public class LoginController {

	@Autowired
	private IAdministradorService administradorService;
	
	@RequestMapping("/login")
	public String Login(Model model,Principal principal,RedirectAttributes flash) {
		
		
		if(principal !=null) {
			flash.addFlashAttribute("info","Ya inicio sesión");
			return "redirect:/";
		}
		
		Administrador administrador = new Administrador();
		model.addAttribute("administrador",administrador);
		return "/administrador/login";
	}
	
	@PostMapping("/login")
	public String ValidarLogin(Administrador administrador,Model model) {
		if(administradorService.findByMailAndPassword(administrador.getMail(), administrador.getPassword())) {
			return "redirect:../administracion/listarProductos";
		}else {
			return "redirect:../administracion/login";
		}
	}
	
	
}