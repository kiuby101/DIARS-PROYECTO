package com.loli.demo.controllers;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loli.demo.models.dao.service.ICategoriaService;
import com.loli.demo.models.dao.service.IProductoService;
import com.loli.demo.models.entity.Categoria;
import com.loli.demo.models.entity.Producto;

@Controller

public class ProductoController {

	@Autowired 
	private IProductoService productoService;
	
	@Autowired
	private ICategoriaService categoriaService;  
	
		
	//método para ver las fotos y detalle del producto
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String , Object> model) {
		
		Producto producto = productoService.buscarProd(id);
		if(producto == null) {
			return "redicet:/listarProductos";
		}
		
		model.put("producto",producto);
		model.put("titulo","detalle del producto:" + producto.getNombre());
		
		return "ver";
	}
	
	@RequestMapping(value = "/listarProductos", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.findAll());
		return "listarProductos";
	}
	
	@RequestMapping("/formularioProd")
	public String Registrar(Model model) {
		Producto productos = new Producto();
		model.addAttribute("titulo", "registro");
		model.addAttribute("productos", productos);
		return "formularioProd";
	}
	
	@RequestMapping(value = "/formularioProd/{id}")
	public String editar(@PathVariable(name="id")Long id, Model model) {
		Producto productos = null;
		if(id>0) {
			productos=productoService.buscarProd(id);
		}else {
			return "redirect:listarProductos ";
		}
		
		model.addAttribute("titulo", "Editar Producto");
		model.addAttribute("productos", productos);
		return "formularioProd";
		
	}
	
	@RequestMapping(value="/formularioProd",method = RequestMethod.POST)
	public String guardarProd(Producto producto, Model model) {
		productoService.guardarProd(producto);
		return "redirect:listarProductos";
		
	}
	
	
	@RequestMapping(value = "/eliminarProd/{id}")
	public String eliminar(@PathVariable(value = "id")Long id) {
		if(id>0) {
			productoService.delete(id);		
		}
		return "redirect:/listarProductos";
	}
	
	//selector categoria
	@ModelAttribute("listarCate")
	public List<Categoria> categoria(){
		return categoriaService.findAll();
	}
}