package com.loli.demo.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.loli.demo.models.dao.service.ICategoriaService;
import com.loli.demo.models.entity.Categoria;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/listarCategoria")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado categoria");
		model.addAttribute("categoria",categoriaService.findAll());
		return "categoria/listarCategoria";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/formularioCategoria")
	public String crear(Map<String, Object> model) {
		Categoria categoria = new Categoria();
		model.put("categoria", categoria);
		model.put("titulo", "registrar Categoria");
		return "categoria/formularioCategoria";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/formularioCategoria/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		Categoria categoria = null;
		if(id>0) {
			categoria = categoriaService.findOne(id);
		}else {
			return "redirect:categoria/listarCategoria";
		}
		model.put("categoria", categoria);
		model.put("titulo", "editar categoria");
		return "categoria/formularioCategoria";
	}
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/formularioCategoria", method = RequestMethod.POST)
	public String guardar(Categoria categoria) {
		categoriaService.guardarCategoria(categoria);
		return "categoria/listarCategoria";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminarCatagoria/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id>0) {
			categoriaService.eliminarCategoria(id);
		}
		return "redirect:/categoria/listarCategoria";
	}
	
	
}
