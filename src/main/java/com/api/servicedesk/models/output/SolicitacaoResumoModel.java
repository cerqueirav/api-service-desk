package com.api.servicedesk.models.output;

import java.time.OffsetDateTime;
import org.springframework.data.domain.Page;

import com.api.servicedesk.enums.Assunto;
import com.api.servicedesk.enums.StatusSolicitacao;
import com.api.servicedesk.models.Solicitacao;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitacaoResumoModel {
	private Long id;
	
	private String complemento;
	
	private Assunto assunto;
	
	private StatusSolicitacao status;
	
	private ClienteResumoModel cliente;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private OffsetDateTime dataCriacao;
	
	public SolicitacaoResumoModel(Solicitacao solicitacao) {
		this.id = solicitacao.getId();
		this.complemento = solicitacao.getComplemento();
		this.assunto = solicitacao.getAssunto();
		this.status = solicitacao.getStatus();
		this.dataCriacao = solicitacao.getDataCriacao();
		this.cliente = ClienteResumoModel.converterCliente(solicitacao.getCliente());
	}

	public static Page<SolicitacaoResumoModel> converterLista(Page<Solicitacao> page) {
		return page.map(SolicitacaoResumoModel::new);
	}
	
	public static SolicitacaoResumoModel converterSolicitacao(Solicitacao solicitacao) {
		SolicitacaoResumoModel solicitacaoResumoModel = new SolicitacaoResumoModel();
		
		solicitacaoResumoModel.setId(solicitacao.getId());
		solicitacaoResumoModel.setAssunto(solicitacao.getAssunto());
		solicitacaoResumoModel.setComplemento(solicitacao.getComplemento());
		solicitacaoResumoModel.setStatus(solicitacao.getStatus());
		solicitacaoResumoModel.setDataCriacao(solicitacao.getDataCriacao());
		solicitacaoResumoModel.setCliente(ClienteResumoModel.converterCliente(solicitacao.getCliente()));
		
		return solicitacaoResumoModel;
	}
}
