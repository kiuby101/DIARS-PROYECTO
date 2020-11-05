package com.loli.demo.models.dao.service;

import java.util.List;

import com.loli.demo.models.entity.Cliente;

public interface IClienteService {
	//lista de cliente de la clase Cliente
		//finall(): retornar todos
		public List<Cliente> finAll();
		
		//guardar un nuevo cliente en la db
		//recibe el objeto cliente
		public void save(Cliente cliente);
		
		//funcion para buscar al usuario mediante su id
		public Cliente findOne(Long id);
		
		//funcion para eliminar al usuario mediante su id
		public void delete(Long id);
}
