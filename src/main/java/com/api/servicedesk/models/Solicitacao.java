package com.api.servicedesk.models;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.api.servicedesk.enums.Assunto;
import com.api.servicedesk.enums.StatusSolicitacao;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_SOLICITACAO")
public class Solicitacao implements Serializable{
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "descricao", length = 200, nullable = false)
	private String descricao;
	
	@Column(name = "assunto_solicitacao", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private Assunto assunto;
	
	@Column(name = "status_solicitacao", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusSolicitacao status;
	
	@CreationTimestamp
	@Column(name = "data_criacao", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime dataCriacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tb_cliente_id", foreignKey = @ForeignKey(name = "fk_solicitacao_cliente"))
	private Cliente cliente;
}
