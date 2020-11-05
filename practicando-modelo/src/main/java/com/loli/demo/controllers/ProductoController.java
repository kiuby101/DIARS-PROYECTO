package com.loli.demo.controllers;

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

import com.loli.demo.models.dao.service.ICategoriaService;
import com.loli.demo.models.dao.service.IDetalleProductoService;
import com.loli.demo.models.dao.service.IProductoService;
import com.loli.demo.models.dao.service.ITipoProdService;
import com.loli.demo.models.entity.Categoria;
import com.loli.demo.models.entity.DetalleProducto;
import com.loli.demo.models.entity.Producto;
import com.loli.demo.models.entity.TipoProducto;

@Controller
public class ProductoController {

	@Autowired 
	private IProductoService productoService;
	
	@Autowired
	private ITipoProdService tipoproductoService;
	
	@Autowired
	private IDetalleProductoService detalleprodService;
	
	@Autowired
	private ICategoriaService categoriaService;  
	
	@RequestMapping(value = "/listarProductos", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "listado de productos");
		model.addAttribute("productos", productoService.findAll());
		return "listarProductos";
	}
	
	@RequestMapping("/formularioProd")
	public String Registrar(Model model) {
		Producto producto = new Producto();
		model.addAttribute("titulo", "registrar producto");
		model.addAttribute("producto", producto);
		return "formularioProd";
	}
	
	@RequestMapping(value = "/editarProd/{id}")
	public String editar(@PathVariable(name="id")Long id, Model model) {
		Producto producto = null;
		List<DetalleProducto> detalles = new ArrayList<>(); 
		if(id>0) {
			producto=productoService.buscarProd(id);
			detalles = detalleprodService.findByProducto(producto);
		}else {
			return "redirect:listarProductos ";
		}
		
		model.addAttribute("titulo", "Editar Producto");
		model.addAttribute("producto", producto);
		model.addAttribute("detalle", detalles);
		return "editar";
		
	}
	
	@PostMapping("/formularioProd")
	public String guardarProd(@RequestParam(name = "tipoDetalle[]",required = false)String [] tipo,
								@RequestParam(name = "detalle[]", required = false)String [] detalle,
								@RequestParam(name = "id[]", required = false)Long[] id, 
								Producto productos, Model model) {
		
		productoService.guardarProd(productos);
		for(int i=0;i<tipo.length;i++) {
			DetalleProducto detalles = new DetalleProducto();
			detalles.setDetalle(detalle[i]);
			detalles.setTipoDetalle(tipo[i]);
			detalles.setProducto(productos);
			detalleprodService.save(detalles);
		}
		return "redirect:listarProductos";
	}
	
	@PostMapping("/formularioEditar")
	public String guardardetalleEditado(@RequestParam(name = "tipoDetalle[]", required =false)String[] tipo,
										@RequestParam(name = "detalle[]", required = false)String[] detalle,
										@RequestParam(name = "detalleid[]", required = false)Long[]id,
										Producto productos, Model model) {
		
		productoService.guardarProd(productos);
		for(int i=0;i<tipo.length;i++) {
			DetalleProducto detalles = new DetalleProducto();
			detalles.setId(id[i]);
			detalles.setDetalle(detalle[i]);
			detalles.setProducto(productos);
			detalleprodService.save(detalles);
		}
		
		return "redirect:listarProductos";
		
	}
	
	
	@RequestMapping(value = "/eliminarProd/{id}")
	public String eliminar(@PathVariable(value = "id")Long id) {
		if(id>0) {
			productoService.delete(id);
		}
		return "redirect:/listarProductos";
	}
	
	//selector tipo producto
	@ModelAttribute("listartipoprod")
	public List<TipoProducto> tipoprod(){
		return tipoproductoService.findAll();
	}
	
	//selector categoria
	@ModelAttribute("listarCate")
	public List<Categoria> categoria(){
		return categoriaService.findAll();
	}
}
