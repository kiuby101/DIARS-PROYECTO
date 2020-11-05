package com.nagro.springboot.app.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagro.springboot.app.entity.dao.IDetalleProductoDao;
import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;

@Service
public class DetalleProductoServiceImpl implements IDetalleProductoService {

	@Autowired
	private IDetalleProductoDao detalleProductoDao;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional
	public void save(DetalleProducto details) {
		// TODO Auto-generated method stub
		//Update
		if(details.getId() !=0 && details.getId() > 0) {
			em.merge(details);
		}else {
			//Create
			em.persist(details);	
		}
	}



	@Override
	@Transactional(readOnly = true)
	public DetalleProducto findById(int id) {
		// TODO Auto-generated method stub
		return detalleProductoDao.findById(id).orElse(null);
	}



	@Override
	@Transactional(readOnly = true)
	public List<DetalleProducto> findByProducto(Producto producto) {
		// TODO Auto-generated method stub
		return detalleProductoDao.findByProducto(producto);
	}

	@Override
	@Transactional
	public void delete(DetalleProducto detalle) {
		// TODO Auto-generated method stub
		em.remove(detalle);
	}


}
