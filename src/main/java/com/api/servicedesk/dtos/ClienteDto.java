package com.api.servicedesk.dtos;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteDto {
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;

	@NotBlank
	private String email;
	
	@NotBlank
	private LocalDate dataNascimento;
	
	@NotBlank
	private String sexo;
	
	private String urlAvatar;
	
	private List<SolicitacaoDto> solicitacoes;
}


