package com.loli.demo.models.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loli.demo.models.dao.IProductoDao;
import com.loli.demo.models.entity.Categoria;
import com.loli.demo.models.entity.Producto;

@Service
public class IProductoServiceImpl implements IProductoService{

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
	public void guardarProd(Producto producto) {
		// TODO Auto-generated method stub
		productoDao.guardarProd(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto buscarProd(Long id) {
		// TODO Auto-generated method stub
		return productoDao.buscarProd(id);
	}

	@Override
	@Transactional
	public void guardarCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		productoDao.guardarCategoria(categoria);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		productoDao.delete(id);
	}
	

}
