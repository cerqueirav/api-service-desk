package com.api.servicedesk.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.models.input.ClienteAtualizarInput;
import com.api.servicedesk.models.input.ClienteNovoInput;
import com.api.servicedesk.models.output.ClienteResumoModel;
import com.api.servicedesk.services.ClienteService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar(){
		var clientes = clienteService.listar();
		
		if (clientes.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
					
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.listar());
	}
	
	@GetMapping("/page/{pagina}")
    public Page<ClienteResumoModel> listar(@PathVariable int pagina, @RequestParam(defaultValue = "3", required = false) int qtd) {
		return clienteService.listar(pagina, qtd);
    }

	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Object> listarPorId(@PathVariable Long clienteId){
		var cliente = clienteService.buscarOuFalhar(clienteId);
		
		if (cliente == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido!");
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarOuFalhar(clienteId));
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody ClienteNovoInput clienteNovoInput) {
		var cliente = new Cliente();
		 
		clienteService.preparaEnumSexoParaCadastro(clienteNovoInput.getSexo(), cliente);
		
		BeanUtils.copyProperties(clienteNovoInput, cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(cliente));
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Object> atualizar(@PathVariable Long clienteId, @RequestBody @Valid ClienteAtualizarInput clienteAtualizarInput) {
		var clienteAtual = clienteService.buscarOuFalhar(clienteId);
		
		if (clienteAtual == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido");
			
		clienteAtual = clienteService.atualizar(clienteAtualizarInput, clienteAtual);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(clienteAtual));
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Object> deletar(@PathVariable Long clienteId){
		var cliente = clienteService.buscarOuFalhar(clienteId);
		
		if (cliente == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido!"); 
		
		clienteService.deletar(clienteId);
		
		return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido com sucesso!");
	}
}
