package com.api.servicedesk.services;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.servicedesk.enums.StatusCliente;
import com.api.servicedesk.exceptions.ClienteNaoEncontradoException;
import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.models.input.ClienteAtualizarInput;
import com.api.servicedesk.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		 preparaClienteParaSalvar(cliente);
		 return clienteRepository.save(cliente);
	}
	
	@Transactional
	public Cliente atualizar(Long clienteId, ClienteAtualizarInput clienteAtualizarInput, Cliente clienteAtual) {
		preparaClienteParaAtualizar(clienteAtualizarInput, clienteAtual);
		
		return clienteRepository.save(clienteAtual);
	}
	
	@Transactional
	public void deletar(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	private void preparaClienteParaSalvar(Cliente clienteInput) {
		clienteInput.setStatus(StatusCliente.Ativo);
		clienteInput.setDataCadastro(OffsetDateTime.now());
	}
	
	// Refatorar depois ...
	private void preparaClienteParaAtualizar(ClienteAtualizarInput clienteAtualizarInput, Cliente cliente) {
		cliente.setNome(clienteAtualizarInput.getNome());
		cliente.setCpf(clienteAtualizarInput.getCpf());
		
		if (clienteAtualizarInput.getLogin() != null)
			cliente.setLogin(clienteAtualizarInput.getLogin());
		
		if (clienteAtualizarInput.getSenha() != null)
			cliente.setSenha(clienteAtualizarInput.getSenha());
		
		if (clienteAtualizarInput.getEmail() != null)
			cliente.setEmail(clienteAtualizarInput.getEmail());
		
		if (clienteAtualizarInput.getDataNascimento() != null)
			cliente.setDataNascimento(clienteAtualizarInput.getDataNascimento());
		
		if (clienteAtualizarInput.getSexo() != null)
			cliente.setSexo(clienteAtualizarInput.getSexo());
		
		if (clienteAtualizarInput.getUrlAvatar() != null)
			cliente.setUrlAvatar(clienteAtualizarInput.getUrlAvatar());	
	}
	
	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
	}
}
