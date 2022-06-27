package com.api.servicedesk.models.output;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;

import com.api.servicedesk.enums.Sexo;
import com.api.servicedesk.enums.StatusCliente;
import com.api.servicedesk.models.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteResumoModel {
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String email;
	
	private LocalDate dataNascimento;
	
	private Sexo sexo;
	
	private String urlAvatar;
	
	private StatusCliente status;
	
	private OffsetDateTime dataCadastro;
	
	public ClienteResumoModel(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.dataNascimento = cliente.getDataNascimento();
		this.sexo = cliente.getSexo();
		this.urlAvatar = cliente.getUrlAvatar();
		this.status = cliente.getStatus();
		this.dataCadastro = cliente.getDataCadastro();
	}

	public static Page<ClienteResumoModel> converterLista(Page<Cliente> page) {
		return page.map(ClienteResumoModel::new);
	}
	
	public static ClienteResumoModel converterCliente(Cliente cliente) {
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
