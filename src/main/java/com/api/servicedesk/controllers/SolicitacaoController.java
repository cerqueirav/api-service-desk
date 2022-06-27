package com.api.servicedesk.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.servicedesk.models.Solicitacao;
import com.api.servicedesk.models.input.SolicitacaoAtualizarInput;
import com.api.servicedesk.models.input.SolicitacaoNovaInput;
import com.api.servicedesk.models.output.SolicitacaoResumoModel;
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
	
	@GetMapping("/page/{pagina}")
    public Page<SolicitacaoResumoModel> listar(@PathVariable int pagina, @RequestParam(defaultValue = "3", required = false) int qtd) {
		return solicitacaoService.listar(pagina, qtd);
    }
	
	@GetMapping("/{solicitacaoId}")
	public ResponseEntity<Object> listarPorId(@PathVariable Long solicitacaoId){
		var solicitacao = solicitacaoService.buscarOuFalhar(solicitacaoId);
		
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
	
	@PutMapping("/{solicitacaoId}")
	public ResponseEntity<Object> atualizar(@PathVariable Long solicitacaoId, @RequestBody @Valid SolicitacaoAtualizarInput solicitacaoAtualizarInput){
		var solicitacao = solicitacaoService.buscarOuFalhar(solicitacaoId);
		
		if(solicitacao == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido");
		
		solicitacao = solicitacaoService.atualizar(solicitacaoAtualizarInput, solicitacao);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacao);
	}
	
	@DeleteMapping("/{solicitacaoId}")
	public ResponseEntity<Object> deletar(@PathVariable Long solicitacaoId){
		var solicitacao = solicitacaoService.buscarOuFalhar(solicitacaoId);
		
		if(solicitacao == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido");
		
		solicitacaoService.deletar(solicitacaoId);
		
		return ResponseEntity.status(HttpStatus.OK).body("Solicitacao excluida com sucesso!");
	}
	
	public boolean clienteExistente(Long clienteId) {
		var cliente = clienteService.buscarOuFalhar(clienteId);
		
		return (cliente != null) ? true : false;
	}
}
