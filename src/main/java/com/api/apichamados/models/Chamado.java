package com.api.apichamados.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chamado")
public class Chamado implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Assunto assunto;
	
	@Column(nullable = false)
	private LocalDate dataRegistro;
	
	@Column(nullable = false)
	private StatusChamado status;
	
	@OneToMany
	@Column(nullable = false)
	private Usuario usuario;
}
