package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDaoRep extends CrudRepository<Cliente, Long>{

	
	public List<Cliente> findByNombreLike(String nombre);
}
