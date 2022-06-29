package com.api.servicedesk.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.servicedesk.enums.StatusCliente;
import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.models.input.ClienteAtualizarInput;
import com.api.servicedesk.models.output.ClienteResumoModel;
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

	@Transactional
	public void deletar(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

    public Page<ClienteResumoModel> listar(int pagina, int qtd){
		Pageable pageable = PageRequest.of(pagina, qtd, Sort.Direction.ASC, "nome");
        return ClienteResumoModel.converterLista(clienteRepository.findAll(pageable));
    }
	
	public void preparaClienteParaSalvar(Cliente clienteInput) {
		clienteInput.setStatus(StatusCliente.Ativo);
		clienteInput.setDataCadastro(OffsetDateTime.now());
	}
	
	// Refatorar depois ...
	private void preparaClienteParaAtualizar(ClienteAtualizarInput clienteAtualizarInput, Cliente cliente) {
		if (clienteAtualizarInput.getNome() != null)
			cliente.setNome(clienteAtualizarInput.getNome());
		
		if (clienteAtualizarInput.getCnpj() != null)
			cliente.setCnpj(clienteAtualizarInput.getCnpj());
		
		if (clienteAtualizarInput.getEndereco() != null)
			cliente.setEndereco(clienteAtualizarInput.getEndereco());
	}
	
	public Optional<Cliente> clienteExistentePorCnpj(String clienteCnpj) {
		Optional<Cliente> clienteExistentePorCpnj = clienteRepository.findByCnpj(clienteCnpj);
		
		if (clienteExistentePorCpnj.isPresent())
			return clienteExistentePorCpnj;
		
		return null;
	}
}
