package com.api.servicedesk.exceptions;

public class SexoInvalidoException extends RuntimeException{

	private static final long serialVersionUID = 3L;

	public SexoInvalidoException(String mensagem) {
		super(mensagem);
	}
	
	public SexoInvalidoException() {
		this(String.format("O sexo informado é invalido!!"));
	}
	
}



