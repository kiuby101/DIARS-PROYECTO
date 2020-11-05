package com.loli.demo.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.loli.demo.models.entity.DetalleProducto;
import com.loli.demo.models.entity.Producto;

public interface IDetalleDao extends CrudRepository<DetalleProducto, Long>{
	
	public List<DetalleProducto> findByProducto(Producto producto);
	
	
}
