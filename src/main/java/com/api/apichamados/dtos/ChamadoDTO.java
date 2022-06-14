package com.api.apichamados.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ChamadoDTO {
	@NotBlank
	private String descricao;

	@NotBlank
	private String assunto;
	
	@NotBlank
	private String status;
	
	@NotBlank
	private String usuarioID;
}
