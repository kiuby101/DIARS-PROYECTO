package com.loli.demo.models.dao;

import java.util.List;

import com.loli.demo.models.entity.Categoria;
import com.loli.demo.models.entity.DetalleProducto;
import com.loli.demo.models.entity.Producto;

public interface IProductoDao {
	
	public List<Producto> findAll();
	
	public void guardarProd(Producto producto);
	
	public Producto buscarProd(Long id);
	
	public void guardarDetalle(DetalleProducto detalle);
	
	public void guardarCategoria(Categoria categoria);
	
	public void delete(Long id);
	
}
