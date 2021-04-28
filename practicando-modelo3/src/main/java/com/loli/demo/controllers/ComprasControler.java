package com.loli.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.loli.demo.models.dao.service.ICarritoService;
import com.loli.demo.models.dao.service.IClienteService;
import com.loli.demo.models.dao.service.IComprasService;
import com.loli.demo.models.dao.service.IProductoService;
import com.loli.demo.models.entity.Carrito;
import com.loli.demo.models.entity.Cliente;
import com.loli.demo.models.entity.Compras;

@Controller
@RequestMapping("/compras")
public class ComprasControler {

	@Autowired
	private IComprasService comprasService;
	
	@Autowired
	private ICarritoService carritoService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IProductoService productoService;
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/ProcesoPago")
	public String Pago(Model model, Authentication authentcation) {
		Compras compras = new Compras();
		Cliente cliente = clienteService.findByEmail(authentcation.getName());
		List<Carrito> carrito = carritoService.findAll();
		
		float total = 0.0f;
		int cantidad = carrito.size();
		
		for(int i=0;i<carrito.size();i++) {
			total+=carrito.get(i).getProducto().getPrecio();
		}
		
		model.addAttribute("titulo", "Proceso de pago");
		model.addAttribute("compras", compras);
		model.addAttribute("carrito", carrito);
		model.addAttribute("cliente", cliente);
		model.addAttribute("total", total);
		model.addAttribute("cantidad", cantidad);
		return "/compras/ProcesoPago";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value="/ProcesoPago", method = RequestMethod.POST)
	public String PagoRealizado(Compras compras, Model model, Authentication authentcation) {
		Cliente cliente = clienteService.findByEmail(authentcation.getName());
		List<Carrito> carrito = carritoService.findAll();
		boolean generado = true;
		
		float total = 0.0f;
		for(int i=0; i<carrito.size();i++) {
			compras.addCarrito(carritoService.findAll().get(i));
			total+=carrito.get(i).getProducto().getPrecio();
		}
		compras.setCliente(cliente);
		compras.setTotal(total);
		compras.setCarrito(carrito);
		
		comprasService.save(compras);
		model.addAttribute("generado", generado);
		return "/carrito/home";
	}
	
}
