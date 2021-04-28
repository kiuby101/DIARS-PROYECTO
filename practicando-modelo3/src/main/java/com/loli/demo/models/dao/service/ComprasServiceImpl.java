package com.loli.demo.models.dao.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loli.demo.models.dao.IComprasDao;
import com.loli.demo.models.entity.Compras;

@Service
public class ComprasServiceImpl implements IComprasService{

	@Autowired
	private IComprasDao comprasDao;
	
	@Override
	@Transactional
	public void save(Compras compras) {
		comprasDao.save(compras);
	}

	@Override
	@Transactional(readOnly = true)
	public Compras findOne(Long id) {
		return comprasDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Compras compras = findOne(id);
		comprasDao.delete(compras);
	}

}
