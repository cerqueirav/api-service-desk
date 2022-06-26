package com.api.servicedesk.models.input;
import javax.validation.constraints.NotBlank;
import com.api.servicedesk.enums.Assunto;

public class SolicitacaoAtualizarInput {
	@NotBlank
	private String descricao;
	
	@NotBlank
	private Assunto assunto;
	
	@NotBlank
	private Long clienteId;
}
