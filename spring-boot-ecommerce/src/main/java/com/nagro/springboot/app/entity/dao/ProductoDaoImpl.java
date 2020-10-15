package com.nagro.springboot.app.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nagro.springboot.app.model.entity.DetalleProducto;
import com.nagro.springboot.app.model.entity.Producto;

@Repository("ProductoDaoJPA")
public class ProductoDaoImpl implements IProductoDao {

	@PersistenceContext
	private EntityManager emProd;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return emProd.createQuery("from Producto").getResultList();
	}

	@Override
	public void GuardarProd(Producto producto) {
		// TODO Auto-generated method stub
		//Update
		if(producto.getId() != 0 && producto.getId() > 0) {
			emProd.merge(producto);
		}else {
			//Create
			emProd.persist(producto);	
		}
	}

	@Override
	public Producto BuscarUno(int id) {
		// TODO Auto-generated method stub
		return emProd.find(Producto.class,id);
	}

	@Override
	public void GuardarDetalle(DetalleProducto detalle) {
		// TODO Auto-generated method stub
		emProd.persist(detalle);
	}

}
