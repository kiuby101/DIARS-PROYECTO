package com.loli.demo.models.dao.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loli.demo.models.dao.ICarritoDao;
import com.loli.demo.models.entity.Carrito;

@Service
public class CarritoServiceImpl implements ICarritoService {
	
	@Autowired
	private ICarritoDao carritoDao;

	@Override
	@Transactional
	public List<Carrito> findAll() {
		return (List<Carrito>) carritoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Carrito carrito) {
		carritoDao.save(carrito);
	}

	@Override
	@Transactional
	public Carrito findOne(Long id) {
		return carritoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Carrito carrito = findOne(id);
		carritoDao.delete(carrito);
	}

}
