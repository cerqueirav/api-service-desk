package com.api.servicedesk.models.input;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteAtualizarInput{
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;

	private String login;
	
	private String senha;
	
	private String email;
	
	private LocalDate dataNascimento;
	
	private String sexo;
	
	private String urlAvatar;
}
