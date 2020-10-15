package com.nagro.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.nagro.springboot.app.entity.dao.ITipoProdDao;
import com.nagro.springboot.app.model.entity.TipoProducto;

@Service
public class TipoProductoServiceImpl implements ITipoProdService {

	@Autowired
	private ITipoProdDao tipoProdDao;
	

	@Override
	@Transactional(readOnly = true)
	public List<TipoProducto> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoProducto>) tipoProdDao.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public TipoProducto findOne(int id) {
		// TODO Auto-generated method stub
		return tipoProdDao.findById(id).orElse(null);
	}


	@Override
	public TipoProducto findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return tipoProdDao.findByNombre(nombre);
	}

}
