package com.nagro.springboot.app.model.service;

import com.nagro.springboot.app.model.entity.Administrador;

public interface IAdministradorService {
	public boolean findByMailAndPassword(String email,String password);
	public void Save(Administrador administrador);
}
