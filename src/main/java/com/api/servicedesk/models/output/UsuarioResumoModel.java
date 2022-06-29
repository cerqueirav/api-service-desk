package com.api.servicedesk.models.output;
import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;
import com.api.servicedesk.models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioResumoModel {	
	private String nome;
	
	private String email;
		
	@JsonFormat(pattern="dd-MM-yyyy")
	private OffsetDateTime dataCadastro;
	
	public UsuarioResumoModel(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.dataCadastro = usuario.getDataCadastro();
	}

	public static Page<UsuarioResumoModel> converterLista(Page<Usuario> page) {
		return page.map(UsuarioResumoModel::new);
	}
	
	public static UsuarioResumoModel converterCliente(Usuario usuario) {
		UsuarioResumoModel usuarioResumoModel = new UsuarioResumoModel();
		
		usuarioResumoModel.setNome(usuario.getNome());
		usuarioResumoModel.setEmail(usuario.getEmail());
		usuarioResumoModel.setDataCadastro(usuario.getDataCadastro());
		
		return usuarioResumoModel;
	}
}
