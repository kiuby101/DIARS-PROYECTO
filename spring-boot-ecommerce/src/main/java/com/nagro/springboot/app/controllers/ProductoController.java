package com.nagro.springboot.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;
import com.nagro.springboot.app.model.entity.TipoProducto;
import com.nagro.springboot.app.model.service.IDetalleProductoService;
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
	
	
	@Autowired
	private IDetalleProductoService detalleProductoService;

	
	@RequestMapping(value={"/listarProductos","/"},method=RequestMethod.GET)
	public String ListarProductos(Model model) {
		model.addAttribute("productos",productoService.findAll());
		return "administrador/productos";
	}
	
	
	@RequestMapping(value="/busqueda")
	public String BuscarProducto(Model model,Producto producto) {
		return "administrador/busqueda";
	}
	
	@RequestMapping("/registrar")
	public String Registrar(Model model) {
		Producto productos = new Producto();
		
		model.addAttribute("titulo", "Registrar");
		
		model.addAttribute("productos", productos);
		
		return "administrador/registrar";
	}
	
	
	@RequestMapping(value = "/editar/{id}")
	public String Editar(@PathVariable(name = "id")int id,Model model) {
		Producto productos=null;
		List<DetalleProducto> detalles=new ArrayList<>();
		if(id>0) {
			productos=productoService.BuscarUno(id);
			detalles=detalleProductoService.findByProducto(productos);
		}else {
			return "redirect:listarProductos";
		}

		model.addAttribute("titulo","Editar Producto");
		model.addAttribute("productos",productos);
		model.addAttribute("detalles",detalles);
		return "administrador/editar";
	}
	
	
	
	
	@PostMapping("/form")
	public String GuardarProducto(@RequestParam(name = "tipoDetalle[]",required = false)String[]tipo,
			@RequestParam(name = "detalle[]",required = false)String[]detalle,
			@RequestParam(name = "id[]",required = false)int[]id,
			Producto productos,Model model,SessionStatus status) {

		productoService.GuardarProd(productos);
		for(int i=0;i<tipo.length;i++) {
			DetalleProducto details=new DetalleProducto();
			details.setDetalle(detalle[i]);
			details.setTipoDetalle(tipo[i]);
			details.setProducto(productos);
			detalleProductoService.save(details);
		}
		
		status.setComplete();
		return "redirect:listarProductos";
	}
	
	@PostMapping("/formeditar")
	public String EditarProductoPost(@RequestParam(name = "tipoDetalle[]",required = false)String[]tipo,
			@RequestParam(name = "detalle[]",required = false)String[]detalle,
			@RequestParam(name = "Detalleid[]",required = false)int[]id,
			Producto productos,Model model,SessionStatus status) {

		productoService.GuardarProd(productos);
		for(int i=0;i<tipo.length;i++) {
			DetalleProducto details=new DetalleProducto();
			details.setId(id[i]);
			details.setDetalle(detalle[i]);
			details.setTipoDetalle(tipo[i]);
			details.setProducto(productos);
			detalleProductoService.save(details);
		}
		
		status.setComplete();
		return "redirect:listarProductos";
	}
	@RequestMapping(value="/eliminar/{id}")
	public String Eliminar(@PathVariable(value="id") int id) {
		if(id>0) {
			Producto producto = productoService.BuscarUno(id);
			List<DetalleProducto> detallesProd = detalleProductoService.findByProducto(producto);
			for(int i=0;i<detallesProd.size();i++) {
				DetalleProducto detalle = detallesProd.get(i);
				detalleProductoService.delete(detalle);
			}
			productoService.delete(id);
		}
		return "redirect:../listarProductos";
	}
	

	//SELECTOR DE TIPO DE PRODUCTO
	@ModelAttribute("listTipProducto")
	public List<TipoProducto> tipoProducto(){
		 return tipoProductoService.findAll();
	}
	
}
