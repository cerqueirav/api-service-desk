package com.api.servicedesk.models.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UsuarioAtualizarInput {
	private String nome;
	
	@NotBlank
	private String email;
	
	private String urlImagem;
}
