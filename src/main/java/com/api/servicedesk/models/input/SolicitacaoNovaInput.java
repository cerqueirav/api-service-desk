package com.api.servicedesk.models.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitacaoNovaInput {
	@NotBlank
	private String assunto;
	
	@NotBlank
	private String clienteCnpj;
	
	@NotBlank
	private String complemento;
}
