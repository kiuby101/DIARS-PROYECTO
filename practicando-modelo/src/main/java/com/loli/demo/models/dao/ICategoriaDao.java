package com.loli.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.loli.demo.models.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long>{

	public Categoria findByNombre(String nombre);
	
}
