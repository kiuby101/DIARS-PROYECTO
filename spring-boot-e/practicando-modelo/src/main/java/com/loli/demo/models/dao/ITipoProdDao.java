package com.loli.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.loli.demo.models.entity.TipoProducto;

public interface ITipoProdDao extends CrudRepository<TipoProducto, Long>{

	public TipoProducto findByNombre(String nombre);
	
}
