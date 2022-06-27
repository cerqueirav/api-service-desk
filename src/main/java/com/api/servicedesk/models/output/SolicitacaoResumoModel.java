package com.api.servicedesk.models.output;

import java.time.OffsetDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import com.api.servicedesk.enums.Assunto;
import com.api.servicedesk.enums.StatusSolicitacao;
import com.api.servicedesk.models.Cliente;
import com.api.servicedesk.models.Solicitacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitacaoResumoModel {
	private Long id;
	
	private String descricao;
	
	private Assunto assunto;
	
	private StatusSolicitacao status;
	
	private ClienteResumoModel cliente;
	
	private OffsetDateTime dataCriacao;
	
	public SolicitacaoResumoModel(Solicitacao solicitacao) {
		this.id = solicitacao.getId();
		this.descricao = solicitacao.getDescricao();
		this.assunto = solicitacao.getAssunto();
		this.status = solicitacao.getStatus();
		this.dataCriacao = solicitacao.getDataCriacao();
		this.cliente = converterCliente(solicitacao.getCliente());
		BeanUtils.copyProperties(solicitacao, solicitacao);
	}

	public static Page<SolicitacaoResumoModel> converterLista(Page<Solicitacao> page) {
		return page.map(SolicitacaoResumoModel::new);
	}
	
	public ClienteResumoModel converterCliente(Cliente cliente) {
		ClienteResumoModel clienteResumo = new ClienteResumoModel();
		
		clienteResumo.setId(cliente.getId());
		clienteResumo.setNome(cliente.getNome());
		clienteResumo.setCpf(cliente.getCpf());
		clienteResumo.setEmail(cliente.getEmail());
		clienteResumo.setDataNascimento(cliente.getDataNascimento());
		clienteResumo.setSexo(cliente.getSexo());
		clienteResumo.setUrlAvatar(cliente.getUrlAvatar());
		clienteResumo.setStatus(cliente.getStatus());
		clienteResumo.setDataCadastro(cliente.getDataCadastro());
		
		return clienteResumo;
	}
}
