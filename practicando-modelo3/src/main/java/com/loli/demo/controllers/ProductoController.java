package com.loli.demo.controllers;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/producto")
public class ProductoController {

	@Autowired 
	private IProductoService productoService;
	
	@Autowired
	private ICategoriaService categoriaService;  
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Secured("ROLE_USER")
	//método para ver las fotos y detalle del producto
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String , Object> model) {
		
		Producto producto = productoService.buscarProd(id);
		if(producto == null) {
			return "redicet:/listarProductos";
		}
		
		model.put("producto",producto);
		model.put("titulo","detalle del producto:" + producto.getNombre());
		
		return "producto/ver";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/listarProductos", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.findAll());
		return "producto/listarProductos";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/formularioProd")
	public String Registrar(Model model) {
		Producto productos = new Producto();
		model.addAttribute("titulo", "registro");
		model.addAttribute("productos", productos);
		return "producto/formularioProd";
	}
	
	double aux= 0.0;
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/formularioProd/{id}")
	public String editar(@PathVariable(name="id")Long id, Model model) {
		Producto productos = null;
		if(id>0) {
			productos=productoService.buscarProd(id);
			aux= productos.getPrecio();
		}else {
			return "redirect:listarProductos ";
		}
		
		model.addAttribute("titulo", "Editar Producto");
		model.addAttribute("productos", productos);
		return "producto/formularioProd";
		
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/formularioProd",method = RequestMethod.POST)
	public String guardarProd(Producto producto, Model model) {
		productoService.guardarProd(producto);
		if(producto.getPrecio() != aux && aux!=0.0) {
			double prod = producto.getPrecio();
			SendMail(aux, prod, producto);
			aux=0.0;
		}
		return "producto/listarProductos";
		
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminarProd/{id}")
	public String eliminar(@PathVariable(value = "id")Long id) {
		if(id>0) {
			productoService.delete(id);		
		}
		return "producto/listarProductos";
	}
	
	//selector categoria
	
	@Secured("ROLE_ADMIN")
	@ModelAttribute("listarCate")
	public List<Categoria> categoria(){
		return categoriaService.findAll();
	}
	
	public void SendMail(double aux, double prod, Producto producto) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setFrom("luislolimott@gmail.com");
		message.setTo("luislolimott@gmail.com");
		
		String mailContent="Precio anterior: "+aux +"  /-/ "+ "precio cambiado: "+prod;
		String mailSubject="Cambios en el precio del producto  "+producto.getNombre();
		
		message.setSubject(mailSubject);
		message.setText(mailContent);
		
		mailSender.send(message);
		
	}
	
}
