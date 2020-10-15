package com.nagro.springboot.app.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nagro.springboot.app.model.entity.Cliente;

//Persistencia a datos
@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

	//importante ponerlo para que dar el contexto
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	//solo por se consulta 
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		//Update
		if(cliente.getId() !=null && cliente.getId() > 0) {
			em.merge(cliente);
		}else {
			//Create
			em.persist(cliente);	
		}
	}

	@Override
	public Cliente findOne(long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class,id);
		
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Cliente cliente = findOne(id);
		em.remove(cliente); 
	}

}
