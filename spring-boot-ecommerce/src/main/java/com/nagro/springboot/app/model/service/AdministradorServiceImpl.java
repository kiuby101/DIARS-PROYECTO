package com.nagro.springboot.app.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagro.springboot.app.entity.dao.IAdministradorDAO;
import com.nagro.springboot.app.model.entity.Administrador;

@Service
public class AdministradorServiceImpl implements IAdministradorService {

    @Autowired
    private IAdministradorDAO administradorDado;


    @Override
    @Transactional(readOnly = true)
    public boolean findByMailAndPassword(String email, String password) {
        // TODO Auto-generated method stub
        return administradorDado.findByMailAndPassword(email, password) != null;
    }


    @Override
    @Transactional
    public void Save(Administrador administrador) {
        administradorDado.save(administrador);

    }

}