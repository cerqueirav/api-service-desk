package com.api.servicedesk.models.input;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import com.api.servicedesk.enums.Sexo;

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
