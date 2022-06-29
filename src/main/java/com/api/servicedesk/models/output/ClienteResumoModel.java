package com.api.servicedesk.models.output;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import com.api.servicedesk.enums.StatusCliente;
import com.api.servicedesk.models.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteResumoModel {	
	private String nome;
	
	private String cnpj;
	
	private String endereco;
	
	private StatusCliente status;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private OffsetDateTime dataCadastro;
	
	public ClienteResumoModel(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cnpj = cliente.getCnpj();
		this.endereco = cliente.getEndereco();
		this.status = cliente.getStatus();
		this.dataCadastro = cliente.getDataCadastro();
	}

	public static Page<ClienteResumoModel> converterLista(Page<Cliente> page) {
		return page.map(ClienteResumoModel::new);
	}
	
	public static ClienteResumoModel converterCliente(Cliente cliente) {
		ClienteResumoModel clienteResumo = new ClienteResumoModel();
		
		clienteResumo.setNome(cliente.getNome());
		clienteResumo.setCnpj(cliente.getCnpj());
		clienteResumo.setEndereco(cliente.getEndereco());
		clienteResumo.setStatus(cliente.getStatus());
		clienteResumo.setDataCadastro(cliente.getDataCadastro());
		
		return clienteResumo;
	}
}
