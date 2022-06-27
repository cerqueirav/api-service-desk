package com.api.servicedesk.exceptions;

public class StatusSolicitacaoInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 3L;

	public StatusSolicitacaoInvalidoException(String mensagem) {
		super(mensagem);
	}
	
	public StatusSolicitacaoInvalidoException() {
		this(String.format("O status da solicitação informado é invalido!!"));
	}
}
