package com.api.servicedesk.models;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_SOLICITACAO")
public class Solicitacao{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", length = 200, nullable = false)
	private String descricao;
	
	@Column(name = "assunto", length = 50, nullable = false)
	private String assunto;
	
	@Column(name = "status", length = 50, nullable = false)
	private String status;
	
	@ManyToOne
	private Cliente cliente;
	
	@CreationTimestamp
	@Column(name = "data_criacao", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime dataCriacao;

	public Solicitacao(Cliente cliente, String descricao, String assunto) {
		this.descricao = descricao;
		this.assunto = assunto;
		this.cliente = cliente;
	}
}
