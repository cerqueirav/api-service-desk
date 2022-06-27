package com.api.servicedesk.services;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.servicedesk.exceptions.ClienteNaoEncontradoException;
import com.api.servicedesk.models.Solicitacao;
import com.api.servicedesk.models.input.SolicitacaoAtualizarInput;
import com.api.servicedesk.models.input.SolicitacaoNovaInput;
import com.api.servicedesk.repositories.SolicitacaoRepository;

@Service
public class SolicitacaoService {
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Transactional
	public Solicitacao salvar(SolicitacaoNovaInput solicitacaoNovaInput) {
		var cliente = clienteService.buscarOuFalhar(solicitacaoNovaInput.getClienteId());
		
		Solicitacao solicitacao = new Solicitacao(cliente, solicitacaoNovaInput.getDescricao(), solicitacaoNovaInput.getAssunto());
		
		BeanUtils.copyProperties(solicitacaoNovaInput, solicitacao);
		
		preparaSolicitacaoParaSalvar(solicitacao);
		
		return solicitacaoRepository.save(solicitacao);
	}
	
	@Transactional
	public Solicitacao atualizar(SolicitacaoAtualizarInput solicitacaoAtualizarInput, Solicitacao solicitacao) {
		preparaSolicitacaoParaAtualizar(solicitacaoAtualizarInput, solicitacao);
		return solicitacaoRepository.save(solicitacao);
	}
	
	@Transactional
	public void deletar(Long solicitacaoId) {
		solicitacaoRepository.deleteById(solicitacaoId);
	}
	
	public List<Solicitacao> listar(){
		return solicitacaoRepository.findAll();
	}
	
	public Object listarPorId(Long solicitacaoId) {
		return solicitacaoRepository.findById(solicitacaoId);
	}
	
	public void preparaSolicitacaoParaSalvar(Solicitacao solicitacao) {
		solicitacao.setStatus("Aberto");
		solicitacao.setDataCriacao(OffsetDateTime.now());
	}
	
	public void preparaSolicitacaoParaAtualizar(SolicitacaoAtualizarInput solicitacaoAtualizarInput, Solicitacao solicitacao) {
		if (solicitacaoAtualizarInput.getAssunto() != null)
			solicitacao.setAssunto(solicitacaoAtualizarInput.getAssunto());
		
		if (solicitacaoAtualizarInput.getDescricao() != null)
			solicitacao.setDescricao(solicitacaoAtualizarInput.getDescricao());
	}
	
	public Solicitacao buscarOuFalhar(Long solicitacaoId) {
		return solicitacaoRepository.findById(solicitacaoId).orElseThrow(() -> new ClienteNaoEncontradoException(solicitacaoId));
	}
}
