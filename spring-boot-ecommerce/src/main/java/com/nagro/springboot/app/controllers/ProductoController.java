package com.nagro.springboot.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;
import com.nagro.springboot.app.model.entity.TipoProducto;
import com.nagro.springboot.app.model.service.IProductoService;
import com.nagro.springboot.app.model.service.ITipoProdService;

@Controller
@RequestMapping("/administracion")
@SessionAttributes("productos")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private ITipoProdService tipoProductoService;
	

	
	@RequestMapping(value="/listarProductos",method=RequestMethod.GET)
	public String ListarProductos(Model model) {
		model.addAttribute("producto",productoService.findAll());
		return "administrador/productos";
	}
	
	@RequestMapping("/registrar")
	public String Registrar(Model model) {
		Producto producto = new Producto();
		
		model.addAttribute("titulo", "Registrar");
		
		model.addAttribute("productos", producto);
		
		return "administrador/registrar";
	}
	
	@PostMapping("/form")
	public String GuardarProducto(Producto producto,Model model) {

		//producto.setTipoProd(tipoProd);
		
		productoService.GuardarProd(producto);
		
		return "redirect:listarProductos";
	}
	

	//SELECTOR DE TIPO DE PRODUCTO
	@ModelAttribute("listTipProducto")
	public List<TipoProducto> tipoProducto(){
		 return tipoProductoService.findAll();
	}
	
}
