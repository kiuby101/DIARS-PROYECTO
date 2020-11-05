package com.loli.demo.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.loli.demo.models.dao.service.ICategoriaService;
import com.loli.demo.models.entity.Categoria;

@Controller
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@RequestMapping(value="/listarCategoria", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado categoria");
		model.addAttribute("categoria",categoriaService.findAll());
		return "listarCategoria";
	}
	
	@RequestMapping(value="/formularioCategoria")
	public String crear(Map<String, Object> model) {
		Categoria categoria = new Categoria();
		model.put("categoria", categoria);
		model.put("titulo", "registrar Categoria");
		return "formularioCategoria";
	}
	
	@RequestMapping(value="/formularioCategoria/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Categoria categoria = null;
		if(id>0) {
			categoria = categoriaService.findOne(id);
		}else {
			return "redirect:/listarCategoria";
		}
		model.put("categoria", categoria);
		model.put("titulo", "editar categoria");
		return "formularioCategoria";
	}
	
	@RequestMapping(value="/formularioCategoria", method = RequestMethod.POST)
	public String guardar(Categoria categoria, Model model) {
		categoriaService.guardarCategoria(categoria);
		return "redirect:listarCategoria";
	}
	
	@RequestMapping(value = "/eliminarCatagoria/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id>0) {
			categoriaService.eliminarCategoria(id);
		}
		return "redirect:/listarCategoria";
	}
	
	
}
