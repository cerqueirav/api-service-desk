package com.api.servicedesk.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.servicedesk.dtos.ClienteDto;
import com.api.servicedesk.models.Cliente;

@Component
public class ClienteInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Cliente toDomainObject(ClienteDto clienteDto) {
		return modelMapper.map(clienteDto, Cliente.class);
	}
	
	public void copyToDomainObject(ClienteDto clienteDto, Cliente cliente) {
		modelMapper.map(clienteDto, cliente);
	}
}
