package com.api.servicedesk.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import com.api.servicedesk.enums.StatusCliente;

@Data
@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(nullable = false, length = 15)
	private String sexo;
	
	@Column(nullable = true,  length = 100)
	private String urlAvatar;
	
	@Column(name = "status", length = 15)
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime dataCadastro;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Solicitacao> solicitacoes = new ArrayList<>();
}
