package com.api.servicedesk.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.servicedesk.enums.Sexo;
import com.api.servicedesk.enums.StatusCliente;
import com.api.servicedesk.exceptions.ClienteNaoEncontradoException;
import com.api.servicedesk.exceptions.SexoInvalidoException;
import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.models.input.ClienteAtualizarInput;
import com.api.servicedesk.models.input.ClienteNovoInput;
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
	public Cliente atualizar(ClienteAtualizarInput clienteAtualizarInput, Cliente clienteAtual) {
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
	
	public void preparaClienteParaSalvar(Cliente clienteInput) {
		clienteInput.setStatus(StatusCliente.Ativo);
		clienteInput.setDataCadastro(OffsetDateTime.now());
	}
	
	public void preparaEnumParaCadastro(ClienteNovoInput clienteNovoInput, Cliente cliente) {
		var sexo = sexoValido(clienteNovoInput.getSexo());
		
		if (sexo == null)
			throw new SexoInvalidoException();
		
		cliente.setSexo(sexo);
	}
	
	public Sexo sexoValido(String sexo) {
		if (sexo.toUpperCase().equals("MASCULINO")) 
			return Sexo.Masculino;
		
		if (sexo.toUpperCase().equals("FEMININO")) 
			return Sexo.Feminino;
		
		return null;
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
		
		if (clienteAtualizarInput.getSexo() != null) {
			var sexo = sexoValido(clienteAtualizarInput.getSexo());
		
			if (sexo == null)
				throw new SexoInvalidoException();
			
			cliente.setSexo(sexo);
		}
			
		
		if (clienteAtualizarInput.getUrlAvatar() != null)
			cliente.setUrlAvatar(clienteAtualizarInput.getUrlAvatar());	
	}
	
	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNaoEncontradoException(clienteId));
	}
	
	public boolean clienteExistentePorCpf(Cliente cliente) {
		Optional<Cliente> clienteExistentePorCpf = clienteRepository.findByCpf(cliente.getCpf());
		
		if (clienteExistentePorCpf.isPresent())
			return true;
		
		return false;
	}
}
