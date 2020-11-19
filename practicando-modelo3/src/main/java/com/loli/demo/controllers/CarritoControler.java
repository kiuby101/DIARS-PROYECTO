package com.loli.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.loli.demo.models.dao.service.ICarritoService;
import com.loli.demo.models.dao.service.IProductoService;
import com.loli.demo.models.entity.Carrito;
import com.loli.demo.models.entity.Producto;

@Controller
public class CarritoControler {
	
	@Autowired 
	private IProductoService productoService;
	
	@Autowired
	private ICarritoService carritoService;
	
	//vista de productos para el usuario
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("contador", contador);
		return "home";
	}

	int contador=0;
	int cantidad=1;
	@RequestMapping(value="/guardar-carrito/{id}", method=RequestMethod.GET)
	public String GuardarCarrito(@PathVariable(value = "id")Long id, Model model) {
		Producto producto = productoService.buscarProd(id);
		contador=contador+1;
		Carrito carrito = new Carrito();
		carrito.setNombres(producto.getNombre());
		carrito.setPrecio(producto.getPrecio());
		carrito.setCantidad(cantidad);
		carrito.setSubtotal(producto.getPrecio()*cantidad);
		carrito.setFoto(producto.getFoto());
		carrito.setProducto(producto);
		carritoService.save(carrito);
		return "redirect:/home";
	}
	
	@RequestMapping(value="/guardar-carrito", method = RequestMethod.GET)
	public String listarcarrito(Model model) {
		double total= 0.0;
		for(long i=1;i<=contador;i++) {
			Carrito carrito = carritoService.findOne(i);
			total=total+carrito.getSubtotal();
		}
		model.addAttribute("carrito", carritoService.findAll());
		model.addAttribute("total", total);
		return "/guardar-carrito";
	}
	
	
}