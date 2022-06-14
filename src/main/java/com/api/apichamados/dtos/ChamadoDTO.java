package com.api.apichamados.dtos;


import com.api.apichamados.models.Assunto;
import com.api.apichamados.models.StatusChamado;
import com.api.apichamados.models.Usuario;

public class ChamadoDTO {
	private String descricao;

	private Assunto assunto;
	

	private StatusChamado status;
	
	
	private Usuario usuario;
}
