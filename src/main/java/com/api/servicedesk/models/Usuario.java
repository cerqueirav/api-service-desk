package com.api.servicedesk.models;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuarios")
public class Usuario {
	 @Column(nullable = false, length = 100)
	 private String nome;
	 
	 @Id
	 private String email;
	 
	 @Column(nullable = true, length = 150)
	 private String urlImagem;
	 
	 @CreationTimestamp
	 @Column(name = "data_cadastro", columnDefinition = "TIMESTAMP WITH TIME ZONE")
	 private OffsetDateTime dataCadastro;
}
