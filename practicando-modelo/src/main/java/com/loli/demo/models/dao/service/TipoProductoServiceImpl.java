package com.loli.demo.models.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loli.demo.models.dao.ITipoProdDao;
import com.loli.demo.models.entity.TipoProducto;

@Service
public class TipoProductoServiceImpl implements ITipoProdService{

	@Autowired
	private ITipoProdDao tipoprodDao;
	
	@Override
	@Transactional
	public List<TipoProducto> findAll() {
		// TODO Auto-generated method stub
		return (List<TipoProducto>) tipoprodDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoProducto findOne(Long id) {
		// TODO Auto-generated method stub
		return tipoprodDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoProducto findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return tipoprodDao.findByNombre(nombre);
	}


	@Override
	@Transactional
	public void guardarTipoProd(TipoProducto tipoProd) {
		// TODO Auto-generated method stub
		tipoprodDao.save(tipoProd);
		
	}

	@Override
	@Transactional
	public void eliminarTipoProd(Long id) {
		// TODO Auto-generated method stub
		tipoprodDao.deleteById(id);
	}
	
}
