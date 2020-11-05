package com.loli.demo.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.loli.demo.models.entity.Categoria;
import com.loli.demo.models.entity.DetalleProducto;
import com.loli.demo.models.entity.Producto;

@Repository
public class ProductoDaoIplm implements IProductoDao{
	
	@PersistenceContext
	private EntityManager emProduc;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findAll() {
		return emProduc.createQuery("from Producto").getResultList();
	}

	@Override
	public void guardarProd(Producto producto) {
		//validar el id
		if(producto.getId()!=null && producto.getId()>0) {
			//actualizar
			emProduc.merge(producto);
		}else {
			//agregar
			emProduc.persist(producto);
		}
		
	}

	@Override
	public Producto buscarProd(Long id) {
		// TODO Auto-generated method stub
		return emProduc.find(Producto.class, id);
	}

	@Override
	public void guardarDetalle(DetalleProducto detalle) {
		// TODO Auto-generated method stub
		emProduc.persist(detalle);
	}

	@Override
	public void guardarCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		emProduc.persist(categoria);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Producto producto = buscarProd(id);
		emProduc.remove(producto);
	}
	
	
	
}
