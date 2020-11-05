package com.nagro.springboot.app.model.service;

import java.util.List;

import com.nagro.springboot.app.model.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
	
	
	public void save(Cliente cliente);
	
	public Cliente findOne(long id);
	
	public void delete(long id);
}
