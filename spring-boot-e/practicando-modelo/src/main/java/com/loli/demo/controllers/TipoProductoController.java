package com.loli.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loli.demo.models.dao.service.ITipoProdService;
import com.loli.demo.models.entity.TipoProducto;

@Controller
public class TipoProductoController {
	
	@Autowired
	private ITipoProdService tipoProdService;
	
	@RequestMapping(value = "/listarTipoProd", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de tipos");
		model.addAttribute("tipoProd", tipoProdService.findAll());
		return "listarTipoProd";
	}
	
	@RequestMapping(value = "/formularioTipoProd")
	public String crearTipo(Model model) {
		TipoProducto tipoProd = new TipoProducto();
		model.addAttribute("tipoProd", tipoProd);
		model.addAttribute("titulo", "registrar tipo");
		return "formularioTipoProd";
	}
	
	@RequestMapping(value = "/formularioTipoProd/{id}")
	public String editarTipo(@PathVariable(value = "id")Long id, Model model) {
		TipoProducto tipoProd = null;
		if(id>0) {
			tipoProd = tipoProdService.findOne(id);
		}else {
			return "redirect:/listarTipoProd";
		}
		model.addAttribute("tipoProd", tipoProd);
		model.addAttribute("titulo", "editar tipo");
		return "formularioTipoProd";
	}
	
	@RequestMapping(value = "/formularioTipoProd", method = RequestMethod.POST)
	public String guardarTipo(TipoProducto tipoProd, Model model) {
		tipoProdService.guardarTipoProd(tipoProd);
		return "redirect:listarTipoProd";
	}
	
	@RequestMapping(value = "/eliminarTipoProd/{id}")
	public String eliminarTipo(@PathVariable(value = "id")Long id) {
		if(id>0) {
			tipoProdService.eliminarTipoProd(id);
		}
		return "redirect:/listarTipoProd";
	}
	
}
