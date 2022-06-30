package com.api.servicedesk.models.input;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteAtualizarInput{
	private String nome;
	@NotBlank
	private String cnpj;

	private String endereco;
	
	private String status;
}
