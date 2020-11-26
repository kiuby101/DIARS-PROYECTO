package com.loli.demo.models.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loli.demo.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
	
	@Query(value = "SELECT * FROM clientes WHERE email=?1",nativeQuery = true)
	public Cliente findByEmail(String email);
	
	
}
