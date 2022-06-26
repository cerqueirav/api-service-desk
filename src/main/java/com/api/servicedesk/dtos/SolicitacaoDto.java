package com.api.servicedesk.dtos;

import javax.validation.constraints.NotBlank;

public class SolicitacaoDto {
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String assunto;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String clienteId;
}
