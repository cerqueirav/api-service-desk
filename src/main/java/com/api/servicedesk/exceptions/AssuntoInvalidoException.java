package com.api.servicedesk.exceptions;

public class AssuntoInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 3L;

	public AssuntoInvalidoException(String mensagem) {
		super(mensagem);
	}
	
	public AssuntoInvalidoException() {
		this(String.format("O assunto informado é invalido!!"));
	}
}



