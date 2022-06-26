package com.api.servicedesk.controllers;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.servicedesk.assembler.ClienteInputDisassembler;
import com.api.servicedesk.dtos.ClienteDto;
import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.services.ClienteService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteInputDisassembler clienteInputDisassembler;
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody ClienteDto clienteDto) {
		var cliente = new Cliente();
		 
		 BeanUtils.copyProperties(clienteDto, cliente);
	
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getClientes(){
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
	}
}
