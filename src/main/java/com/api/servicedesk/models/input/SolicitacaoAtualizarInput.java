package com.api.servicedesk.models.input;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SolicitacaoAtualizarInput {
	private String descricao;
	
	private String assunto;
	
	private String statusSolicitacao;
	
	private Long clienteId;
}
