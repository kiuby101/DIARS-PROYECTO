package com.nagro.springboot.app.model.service;

import java.util.List;

import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;

public interface IDetalleProductoService {

	public void save(DetalleProducto details);
	public DetalleProducto findById(int id);
	public List<DetalleProducto> findByProducto(Producto producto);
	public void delete(DetalleProducto detalle);
}
