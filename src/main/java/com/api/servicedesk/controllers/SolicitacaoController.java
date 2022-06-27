package com.api.servicedesk.controllers;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.servicedesk.models.Solicitacao;
import com.api.servicedesk.models.input.SolicitacaoNovaInput;
import com.api.servicedesk.services.ClienteService;
import com.api.servicedesk.services.SolicitacaoService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
	@Autowired
	private SolicitacaoService solicitacaoService;
	
	@Autowired
	private ClienteService clienteService;
		
	@GetMapping
	public ResponseEntity<List<Solicitacao>> listar(){
		var solicitacoes = solicitacaoService.listar();
		
		if (solicitacoes.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(solicitacoes);
		
		return ResponseEntity.status(HttpStatus.OK).body(solicitacoes);
	}
	
	@GetMapping("/{solicitacaoId}")
	public ResponseEntity<Object> listarPorId(@PathVariable Long solicitacaoId){
		var solicitacao = solicitacaoService.buscarOuFalhar(null);
		
		if (solicitacao == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(solicitacao);
		
		return ResponseEntity.status(HttpStatus.OK).body(solicitacao);
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody SolicitacaoNovaInput solicitacaoNovaInput){
		
		if(!clienteExistente(solicitacaoNovaInput.getClienteId())) 
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro desconhecido");
					
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.salvar(solicitacaoNovaInput));
	}
	
	public boolean clienteExistente(Long clienteId) {
		var cliente = clienteService.buscarOuFalhar(clienteId);
		
		return (cliente != null) ? true : false;
	}
}
