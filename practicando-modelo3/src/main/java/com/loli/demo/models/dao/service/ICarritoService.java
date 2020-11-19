package com.loli.demo.models.dao.service;

import java.util.List;

import com.loli.demo.models.entity.Carrito;

public interface ICarritoService {
	public List<Carrito> findAll();
	public void save(Carrito carrito);
	public Carrito findOne(Long id); 
	public void delete(Long id);
}
