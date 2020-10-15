package com.nagro.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import com.nagro.springboot.app.model.entity.Cliente;
import com.nagro.springboot.app.model.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	//variable que contiene los metodos - Qualifer si se contiene muchos beans ambiguos
	@Autowired
	private IClienteService clienteService;
	
	
	@RequestMapping(value="/listar",method=RequestMethod.GET)
	public String Listar(Model model) {
		model.addAttribute("Titulo","LIstado de Clientes");
		model.addAttribute("clientes",clienteService.findAll());
		return "listar";
	}
	
	
	@RequestMapping(value="/form")
	public String Crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo","Formulario de Cliente");
		model.addAttribute("cliente",cliente);
		
		return "form";
	}
	
	
	@RequestMapping(value="/form/{id}")
	public String Editar(@PathVariable(value="id") Long id,Model model) {
		Cliente cliente = null;
		if(id>0) {
			cliente=clienteService.findOne(id);
		}else {
			return "redirect:/listar";
		}

		model.addAttribute("titulo","Editar Cliente");
		model.addAttribute("cliente",cliente);
		
		return "form";
	}
	
	
	@RequestMapping(value="/form",method=RequestMethod.POST)
	public String Guardar(@Valid Cliente cliente, BindingResult result,Model model,SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo","Formulario de Cliente");
			return "form";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String Eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			clienteService.delete(id);
		}
		return "redirect:/listar";
	}
	
}
