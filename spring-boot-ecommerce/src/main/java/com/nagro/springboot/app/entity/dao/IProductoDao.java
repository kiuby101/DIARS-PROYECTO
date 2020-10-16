package com.nagro.springboot.app.entity.dao;

import java.util.List;

import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;

public interface IProductoDao {
	
	public List<Producto>findAll();
	
	public void GuardarProd(Producto producto);
	
	public Producto BuscarUno(Long id);
	
	public void GuardarDetalle(DetalleProducto detalle);
	
	public void eliminar(Long id);
}
