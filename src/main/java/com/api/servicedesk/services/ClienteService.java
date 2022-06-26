package com.api.servicedesk.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente save(Cliente cliente) {
		 return clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
}
