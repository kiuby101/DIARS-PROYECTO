package com.loli.demo.models.dao.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loli.demo.models.dao.IDetalleDao;
import com.loli.demo.models.entity.DetalleProducto;
import com.loli.demo.models.entity.Producto;

@Service
public class IDetalleProductoServiceImpl implements IDetalleProductoService{

	@Autowired
	private IDetalleDao detalleDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void save(DetalleProducto detalle) {
		// TODO Auto-generated method stub
		if(detalle.getId() != null && detalle.getId()>0) {
			em.merge(detalle);
		}else {
			em.persist(detalle);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public DetalleProducto findById(Long id) {
		// TODO Auto-generated method stub
		return detalleDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DetalleProducto> findByProducto(Producto producto) {
		// TODO Auto-generated method stub
		return detalleDao.findByProducto(producto);
	}

	
}
