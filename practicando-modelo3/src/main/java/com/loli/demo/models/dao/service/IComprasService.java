package com.loli.demo.models.dao.service;

import java.util.List;

import com.loli.demo.models.entity.Compras;

public interface IComprasService {
	public void save(Compras compras);
	public Compras findOne(Long id); 
	public void delete(Long id);
}
