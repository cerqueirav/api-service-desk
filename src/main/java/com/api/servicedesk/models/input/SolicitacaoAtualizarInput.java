package com.api.servicedesk.models.input;
import javax.validation.constraints.NotBlank;
import com.api.servicedesk.enums.Assunto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitacaoAtualizarInput {
	private String descricao;
	
	private Assunto assunto;
	
	@NotBlank
	private Long clienteId;
}
