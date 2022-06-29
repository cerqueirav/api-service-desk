package com.api.servicedesk.services;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.servicedesk.enums.Assunto;
import com.api.servicedesk.enums.StatusSolicitacao;
import com.api.servicedesk.exceptions.AssuntoInvalidoException;
import com.api.servicedesk.exceptions.ClienteNaoEncontradoException;
import com.api.servicedesk.exceptions.StatusSolicitacaoInvalidoException;
import com.api.servicedesk.models.Solicitacao;
import com.api.servicedesk.models.input.SolicitacaoAtualizarInput;
import com.api.servicedesk.models.input.SolicitacaoNovaInput;
import com.api.servicedesk.models.output.SolicitacaoResumoModel;
import com.api.servicedesk.repositories.SolicitacaoRepository;

@Service
public class SolicitacaoService {
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Transactional
	public Solicitacao salvar(SolicitacaoNovaInput solicitacaoNovaInput) {
		var cliente = clienteService.clienteExistentePorCnpj(solicitacaoNovaInput.getClienteCnpj());
		
		Solicitacao solicitacao = new Solicitacao(cliente.get(), solicitacaoNovaInput.getComplemento());
		
		BeanUtils.copyProperties(solicitacaoNovaInput, solicitacao);
		
		preparaEnumAssuntoParaCadastro(solicitacaoNovaInput.getAssunto(), solicitacao);	
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
	
	public Page<SolicitacaoResumoModel> listar(int pagina, int qtd){
		Pageable pageable = PageRequest.of(pagina, qtd, Sort.Direction.ASC, "cliente_nome");
        return SolicitacaoResumoModel.converterLista(solicitacaoRepository.findAll(pageable));
    }
	
	public Object listarPorId(Long solicitacaoId) {
		return solicitacaoRepository.findById(solicitacaoId);
	}
	
	public void preparaSolicitacaoParaSalvar(Solicitacao solicitacao) {
		solicitacao.setStatus(StatusSolicitacao.Aberto);
		solicitacao.setDataCriacao(OffsetDateTime.now());
	}
	
	public void preparaSolicitacaoParaAtualizar(SolicitacaoAtualizarInput solicitacaoAtualizarInput, Solicitacao solicitacao) {
		if (solicitacaoAtualizarInput.getComplemento() != null)
			solicitacao.setComplemento(solicitacaoAtualizarInput.getComplemento());
		
		if (solicitacaoAtualizarInput.getAssunto() != null) 
			preparaEnumAssuntoParaCadastro(solicitacaoAtualizarInput.getAssunto(), solicitacao);
		
		if (solicitacaoAtualizarInput.getStatusSolicitacao() != null)
			preparaEnumStatusParaCadastro(solicitacaoAtualizarInput.getStatusSolicitacao(), solicitacao);
	}
	
	public void preparaEnumAssuntoParaCadastro(String assuntoAtual, Solicitacao solicitacao) {
		var assunto = assuntoValido(assuntoAtual);
		
		if (assunto == null)
			throw new AssuntoInvalidoException();
		
		solicitacao.setAssunto(assunto);
	}
	
	public void preparaEnumStatusParaCadastro(String statusAtual, Solicitacao solicitacao) {
		var status = statusValido(statusAtual);
		
		if (status == null)
			throw new StatusSolicitacaoInvalidoException();
		
		solicitacao.setStatus(status);
	}
	
	public StatusSolicitacao statusValido(String status) {
		if (status.toUpperCase().equals("ABERTO")) 
			return StatusSolicitacao.Aberto;
		
		if (status.toUpperCase().equals("PROGRESSO")) 
			return StatusSolicitacao.Progresso;
		
		if (status.toUpperCase().equals("ATENDIDO")) 
			return StatusSolicitacao.Atendido;
		
		return null;
	}
	
	public Assunto assuntoValido(String assunto) {
		if (assunto.toUpperCase().equals("SUPORTE")) 
			return Assunto.Suporte;
		
		if (assunto.toUpperCase().equals("FINANCEIRO")) 
			return Assunto.Financeiro;
		
		if (assunto.toUpperCase().equals("VISITA")) 
			return Assunto.Visita;
		
		return null;
	}
	
	public Solicitacao buscarOuFalhar(Long solicitacaoId) {
		return solicitacaoRepository.findById(solicitacaoId).orElseThrow(() -> new ClienteNaoEncontradoException(solicitacaoId));
	}
}
