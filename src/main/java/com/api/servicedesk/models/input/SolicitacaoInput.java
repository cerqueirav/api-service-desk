package com.api.servicedesk.models.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitacaoInput {
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String assunto;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String clienteId;
}
