package com.loli.demo.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loli.demo.models.dao.service.IClienteService;
import com.loli.demo.models.entity.Cliente;


//configurar la clase como un controlador
@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	//busca el bin con esa interface (hace mas facil crear un objeto)
	@Autowired
	//seleccionar el bin concreto, usar cuando hay varios componentes implementados
	//@Qualifier("clienteDaoJPA")
	//importando la interface y creando un objeto
	private IClienteService clienteService;

	//value : ruta
	//method=RequestMethod.GET =  tipo de metodo de la peticion
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	//model : importa datos hacia la vista
	public String listar(Model model) {
		//mandamos un titutlo con el mensaje listado de clientes
		model.addAttribute("titulo","Listado de clientes");
		//pasando el listado de clientes a la vista
		model.addAttribute("clientes",clienteService.finAll());
		//retornar el nombre de la lista
		return "cliente/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/crear") 
	public String crear(Map<String, Object> model) {
		//crear el objeto cliente
		Cliente cliente = new Cliente();
		//mandar el objeto cliente a la vista
		model.put("cliente", cliente);
		//mandar el titulo a la vista
		model.put("titulo", "Formulario de cliente");
		return "cliente/form";
	}
	
	//enviar el id como parametro a la ruta
		@Secured("ROLE_USER")
		@RequestMapping(value="/form/{id}")
		//@PathVariable= anotacion para que funcione el envio de parametro a la ruta
		public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
			
			Cliente cliente = null;
			
			if(id>0) {
				cliente = clienteService.findOne(id);
				if(cliente == null) {
					flash.addFlashAttribute("error", "El id del cliente no existe en la BD!");
					return "redirect:cliente/listar";
				}
			}else {
				flash.addFlashAttribute("error", "El id del cliente no puede ser cero!");
				return "redirect:cliente/listar";
			}
			//pasar los datos del cliente a la vista
			model.put("cliente", cliente);
			//pasar titulo a la vista
			model.put("titulo", "editar cliente");
			return "cliente/form";
		}
	
	
	//metodo que se encarga de procesar los datos que envia el usuario
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/form", method = RequestMethod.POST)
	//recibir los datos del cliente
	//@valid= habilitar la validacion
	//BindingResult = muestra los mensajes de errores al usuario
	public String guardar(@Valid Cliente cliente , BindingResult result, Model model, RedirectAttributes flash ) {
		//si contiene errores
		if(result.hasErrors()) {
			//retornar titulo
			//el cliente se pasa de manera automática siempre que se llame igual que el tipo de variable (no importan las mayusculas/minusculas)
			model.addAttribute("titulo", "Formulario de cliente");
			//retornamos la vista del formulario con los mensajes de error
			return "cliente/form";
		}
		
		//variable para el mensaje , validando si edita o guarda
		String mensajeFlash = (cliente.getId() != null)? "cliente editado con éxito!" : "cliente creado con éxito";
		
		String passEnc=passwordEncoder.encode(cliente.getPassword());
		
		cliente.setPassword(passEnc);


		//guardar al objeto
		clienteService.save(cliente);
		
		//mensage flash para mostrarlo al cliente
		flash.addFlashAttribute("success", mensajeFlash);
		//retornamos la vista redirigiendo al listar
		return "redirect:cliente/listar";
	}
	
	//enviar el id como parametro a la ruta
	@Secured("ROLE_USER")
	@RequestMapping(value="/eliminar/{id}")
	//para que funcione el envio del parametro usar @pathvariable
	public String eliminar(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		//validando si el id es mayor a 0
		if(id>0) {
			//si el id es mayor a 0 elimininarlo
			clienteService.delete(id);
			//mensage flash para mostrarlo al cliente
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
		}
		//redirigir a la vista listar
		return "redirect:cliente/listar";
	}
	
	
}
