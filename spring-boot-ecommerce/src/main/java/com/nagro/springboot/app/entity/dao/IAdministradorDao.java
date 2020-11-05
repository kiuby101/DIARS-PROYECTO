package com.nagro.springboot.app.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.nagro.springboot.app.model.entity.Administrador;

public interface IAdministradorDao extends CrudRepository<Administrador, Integer> {

	public Administrador findByMailAndPassword(String email,String password);
	
}
