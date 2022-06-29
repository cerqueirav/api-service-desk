package com.api.servicedesk.models;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.api.servicedesk.enums.Assunto;
import com.api.servicedesk.enums.StatusSolicitacao;

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
	
	@Column(name = "complemento", length = 200, nullable = false)
	private String complemento;
	
	@Enumerated(EnumType.STRING)
	private Assunto assunto;
	
	@Enumerated(EnumType.STRING)
	private StatusSolicitacao status;
	
	@ManyToOne
	private Cliente cliente;
	
	@CreationTimestamp
	@Column(name = "data_criacao", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime dataCriacao;

	public Solicitacao(Cliente cliente, String complemento) {
		this.complemento = complemento;
		this.cliente = cliente;
	}
}
