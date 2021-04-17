package com.bolsadeideas.springboot.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.dao.IClienteDaoRep;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImp implements IClienteService{
	
	@Autowired
	private IClienteDaoRep clienteDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>)clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		//return clienteDao.findOne(id);
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		//clienteDao.delete(id);
		clienteDao.deleteById(id);
	}

	@Override
	public List<Cliente> findByNombreLike(String nombre) {
		// TODO Auto-generated method stub
		
		System.out.println("busqueda por nombre like");
		return clienteDao.findByNombreLike("%"+nombre+"%");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable page) {
		
		return clienteDao.findAll(page);
	}
	
	

}
