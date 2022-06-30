package com.api.servicedesk.controllers;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteResumoModel>> listar(){
		List<ClienteResumoModel> clientesOut = new ArrayList<>();
		
		var clientes = clienteService.listar();
		
		for (int i=0; i<clientes.size(); i++) 
			clientesOut.add(ClienteResumoModel.converterCliente(clientes.get(i)));	
		
		if (clientes.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
					
		return ResponseEntity.status(HttpStatus.OK).body(clientesOut);
	}
	
	@GetMapping("/page/{pagina}")
    public Page<ClienteResumoModel> listar(@PathVariable int pagina, @RequestParam(defaultValue = "3", required = false) int qtd) {
		return clienteService.listar(pagina, qtd);
    }

	
	@GetMapping("/{clienteCnpj}")
	public ResponseEntity<Object> listarPorCnpj(@PathVariable String clienteCnpj){
		var cliente = clienteService.clienteExistentePorCnpj(clienteCnpj);
		
		if (cliente == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido!");
		
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody ClienteNovoInput clienteNovoInput) {
		var cliente = new Cliente();
		
		BeanUtils.copyProperties(clienteNovoInput, cliente);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(cliente));
	}
	
	@PatchMapping("/{clienteCnpj}")
	public ResponseEntity<Object> atualizar(@PathVariable String clienteCnpj, @RequestBody @Valid ClienteAtualizarInput clienteAtualizarInput) {
		var clienteAtual = clienteService.clienteExistentePorCnpj(clienteCnpj);
		
		if (clienteAtual == null) 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro desconhecido");
		
		var clienteAtualizado =	clienteService.atualizar(clienteAtualizarInput, clienteAtual);
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.salvar(clienteAtualizado));
	}
	
	@DeleteMapping("/{clienteCnpj}")
	public ResponseEntity<Object> deletar(@PathVariable String clienteCnpj){
		var cliente = clienteService.clienteExistentePorCnpj(clienteCnpj);
		
		if (cliente == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Erro desconhecido!"); 
		
		clienteService.deletar(cliente);
		
		return ResponseEntity.status(HttpStatus.OK).body("Cliente excluido com sucesso!");
	}
}
