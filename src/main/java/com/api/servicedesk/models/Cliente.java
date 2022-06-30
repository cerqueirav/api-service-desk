package com.api.servicedesk.models;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.api.servicedesk.enums.StatusCliente;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente{
	@EqualsAndHashCode.Include
	@Id
	@Column(nullable = false, length = 100)
	private String cnpj;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100)
	private String endereco;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime dataCadastro;
}
