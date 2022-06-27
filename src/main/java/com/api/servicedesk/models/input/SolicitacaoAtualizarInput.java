package com.api.servicedesk.models.input;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitacaoAtualizarInput {
	private String descricao;
	
	private String assunto;
	
	@NotBlank
	private Long clienteId;
}
