package com.api.servicedesk.services;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.api.servicedesk.dtos.ClienteDto;
import com.api.servicedesk.enums.StatusCliente;
import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.repositories.ClienteRepository;

public class CadastroClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		preparaClienteParaSalvar(cliente);

		cliente = clienteRepository.save(cliente);

		return cliente;
	}
	
	private void preparaClienteParaSalvar(Cliente cliente) {
		cliente.setStatus(StatusCliente.Ativo);
		cliente.setDataCadastro(OffsetDateTime.now());
	}

}
