package com.loli.demo.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.loli.demo.models.entity.Cliente;

//repository : marcar la clase como componente dao
@Repository
public class ClienteDaoImpl implements IClienteDao{
	
	//
	@PersistenceContext
	//em : maneja las clases de entidades(todas las operaciones a la based)
	private EntityManager em;
	
	//suprimir mensaje de error
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> finAll() {
		// TODO Auto-generated method stub
		//retorna el listado de cliente
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) {
		//si el id no es nulo y es mayor a 0
		if(cliente.getId() != null && cliente.getId()>0) {
			//actualizar los datos existentes en la based
			em.merge(cliente);
		}else {
			//almacena el objeto cliente en la base de datos
			em.persist(cliente);
		}
	}

	@Override
	public Cliente findOne(Long id) {
		//buscar el cliente por su id
		return em.find(Cliente.class, id);
	}

	@Override
	public void delete(Long id) {
		//buscar al usuario por su id y eliminarlo con remove
		em.remove(findOne(id));
	}

}
