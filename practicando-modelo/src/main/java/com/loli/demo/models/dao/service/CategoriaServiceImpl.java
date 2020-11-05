package com.loli.demo.models.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loli.demo.models.dao.ICategoriaDao;
import com.loli.demo.models.entity.Categoria;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return (List<Categoria>) categoriaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findOne(Long id) {
		// TODO Auto-generated method stub
		return categoriaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return categoriaDao.findByNombre(nombre);
	}

	@Override
	@Transactional
	public void guardarCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		categoriaDao.save(categoria);
	}
	
	@Override
	@Transactional
	public void eliminarCategoria(Long id) {
		// TODO Auto-generated method stub
		categoriaDao.deleteById(id);
	}
	
	

}
