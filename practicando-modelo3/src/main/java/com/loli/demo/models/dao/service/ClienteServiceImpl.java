package com.loli.demo.models.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loli.demo.models.dao.IClienteDao;
import com.loli.demo.models.entity.Cliente;

//service : denota a la clase como clase service
//otorga un unico punto de acceso a diferentes dao y repositorio
//accede a las funciones del dao como unico punto de acceso
//maneja las transacciones sin tener que implementar los transactional en el dao(implementarlas en el service)
//dentro de un metodo en la clase service se puede interactuar con diferentes Dao
@Service
public class ClienteServiceImpl  implements IClienteService{

	//busca el bin de la interface ICLienteDao
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	//marcar el metodo como de lectura ya que es solo una consulta(buscar es de lectura)
	@Transactional(readOnly = true)
	public List<Cliente> finAll() {
		// TODO Auto-generated method stub
		return clienteDao.findAll();
	}

	@Override
	//marcar el metodo como escritura, para insertar un nuevo registro
	@Transactional
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.save(cliente);
	}

	@Override
	//denotar el metodo como lectura, para buscar al cliente por su id
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	//denotar el metodo como escritura, el metodo remove busca al usuario y lo elimina
	//remove actualiza la tabla y debe ser de escritura
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Cliente cliente = findOne(id);
		clienteDao.delete(cliente);
	}

	@Override
	@Transactional
	public Cliente findByEmail(String mail) {
		return clienteDao.findByEmail(mail);
	}

}
