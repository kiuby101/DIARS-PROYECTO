package com.nagro.springboot.app.model.service;

import java.util.List;


import com.nagro.springboot.app.model.entity.TipoProducto;

public interface ITipoProdService {
	public List<TipoProducto> findAll();
	public TipoProducto findOne(int id);
	public TipoProducto findByNombre(String nombre);
}
