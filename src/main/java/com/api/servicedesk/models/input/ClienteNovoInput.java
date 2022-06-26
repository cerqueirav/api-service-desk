package com.api.servicedesk.models.input;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteNovoInput{
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;

	@NotBlank
	private String login;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private LocalDate dataNascimento;
	
	@NotBlank
	private String sexo;
	
	private String urlAvatar;
	
	private List<SolicitacaoInput> solicitacoes;
}


