package com.api.servicedesk.models;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.api.servicedesk.enums.Sexo;
import com.api.servicedesk.enums.StatusCliente;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente{
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, unique = true, length = 15)
	private String cpf;
	
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(nullable = false, unique = true, length = 100)
	private String login;
	
	@Column(nullable = false, length = 100)
	private String senha;
	
	@Column(nullable = false, columnDefinition = "DATE")
	private LocalDate dataNascimento;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Column(nullable = true,  length = 100)
	private String urlAvatar;
	
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime dataCadastro;
}
