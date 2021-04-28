package com.loli.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.loli.demo.models.dao.ICarritoDao;
import com.loli.demo.models.dao.service.ICarritoService;
import com.loli.demo.models.dao.service.IProductoService;
import com.loli.demo.models.entity.Carrito;
import com.loli.demo.models.entity.Producto;

@Controller
@RequestMapping("/carrito")
public class CarritoControler {
	
	@Autowired 
	private IProductoService productoService;
	
	@Autowired
	private ICarritoService carritoService;
	
	@Autowired
	private ICarritoDao carritoDao;
	
	//vista de productos para el usuario

	@RequestMapping(value={"/home","/"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("contador", carritoService.findAll().size());
		return "carrito/home";
	}
	
	int cantidad=1;
	double total= 0.0;
	@RequestMapping(value="/guardar-carrito/{id}", method=RequestMethod.GET)
	public String GuardarCarrito(@PathVariable(value = "id")Long id, Model model) {
		Producto producto = productoService.buscarProd(id);
		Carrito carrito = new Carrito();
		carrito.setNombres(producto.getNombre());
		carrito.setPrecio(producto.getPrecio());
		carrito.setCantidad(cantidad);
		carrito.setSubtotal(producto.getPrecio()*cantidad);
		carrito.setFoto(producto.getFoto());
		carrito.setProducto(producto);
		total=total+(producto.getPrecio()*cantidad);
		carritoService.save(carrito);
		
		return "redirect:/carrito/home";
	}
	

	@RequestMapping(value="/guardar-carrito", method = RequestMethod.GET)
	public String listarcarrito(Model model) {
		model.addAttribute("carrito", carritoService.findAll());
		model.addAttribute("total", total);
		return "carrito/guardar-carrito";
	}
	

	@RequestMapping(value="/comprar/{id}", method = RequestMethod.GET)
	public String comprar(@PathVariable(value="id")Long id,Model model) {
		Producto producto = productoService.buscarProd(id);
		Carrito carrito = new Carrito();
		carrito.setNombres(producto.getNombre());
		carrito.setPrecio(producto.getPrecio());
		carrito.setCantidad(cantidad);
		carrito.setSubtotal(producto.getPrecio()*cantidad);
		carrito.setFoto(producto.getFoto());
		carrito.setProducto(producto);
		total = total+(producto.getPrecio()*cantidad);
		carritoService.save(carrito);
		model.addAttribute("carrito", carritoService.findAll());
		model.addAttribute("total", total);
		return "carrito/guardar-carrito";
	}
	
	
	
	@RequestMapping(value="/cancelarTodo")
	public String cancelar(Model model) {
		carritoDao.deleteAll();
		total=0.0;
		return "redirect:/carrito/home"; 
	}
	
	
}
