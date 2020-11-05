package com.loli.demo.models.dao.service;

import java.util.List;

import com.loli.demo.models.entity.Categoria;

public interface ICategoriaService {
	
	public List<Categoria> findAll();
	
	public Categoria findOne(Long id);
	
	public Categoria findByNombre(String nombre);
	
	public void guardarCategoria(Categoria categoria);
	
	public void eliminarCategoria (Long id);
	
}
