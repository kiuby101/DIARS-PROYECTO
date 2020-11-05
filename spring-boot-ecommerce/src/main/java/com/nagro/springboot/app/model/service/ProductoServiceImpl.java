package com.nagro.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagro.springboot.app.entity.dao.IProductoDao;
import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return productoDao.findAll();
	}

	@Override
	@Transactional
	public void GuardarProd(Producto producto) {
		// TODO Auto-generated method stub
		productoDao.GuardarProd(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto BuscarUno(int id) {
		// TODO Auto-generated method stub
		return productoDao.BuscarUno(id);
	}

	@Override
	@Transactional
	public void GuardarDetalle(DetalleProducto detalle) {
		// TODO Auto-generated method stub
		productoDao.GuardarDetalle(detalle);
	}

	@Override
	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		 productoDao.delete(id);
	}

}
