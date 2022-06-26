package com.api.servicedesk.exceptions;


public class SolicitacaoNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SolicitacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public SolicitacaoNaoEncontradaException(Long solicitacaoId) {
		this(String.format("Não existe uma solicitacao com código %d", solicitacaoId));
	}
}

