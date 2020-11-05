package com.loli.demo.models.dao.service;

import java.util.List;

import com.loli.demo.models.entity.TipoProducto;

public interface ITipoProdService {
	
	public List<TipoProducto> findAll();
	
	public TipoProducto findOne(Long id);
	
	public TipoProducto findByNombre(String nombre);
	
	public void guardarTipoProd(TipoProducto tipoProd);
	
	public void eliminarTipoProd (Long id);
}