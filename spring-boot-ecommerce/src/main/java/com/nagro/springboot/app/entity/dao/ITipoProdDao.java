package com.nagro.springboot.app.entity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nagro.springboot.app.model.entity.TipoProducto;

public interface ITipoProdDao extends CrudRepository<TipoProducto, Integer> {
	
	public TipoProducto findByNombre(String nombre);
	
}
