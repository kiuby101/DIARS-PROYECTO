package com.loli.demo.models.dao.service;

import java.util.List;

import com.loli.demo.models.entity.DetalleProducto;
import com.loli.demo.models.entity.Producto;

public interface IDetalleProductoService {
	
	public void save(DetalleProducto detalle);
	
	public DetalleProducto findById(Long id);
	
	public List<DetalleProducto> findByProducto(Producto producto);

}
