package com.api.apichamados.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
	private String assunto;
	
	@Column(nullable = false)
	private LocalDate dataRegistro;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private String usuarioID;
}
