package com.nagro.springboot.app.entity.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;

public interface IDetalleProductoDao extends CrudRepository<DetalleProducto, Integer> {
	
	public List<DetalleProducto> findByProducto(Producto producto);
	public void delete(DetalleProducto detalle);
	
}
