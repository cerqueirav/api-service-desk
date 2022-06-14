package com.api.apichamados.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apichamados.dtos.ChamadoDTO;
import com.api.apichamados.models.Chamado;
import com.api.apichamados.services.ChamadoService;

@RestController
@RequestMapping("/chamado")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChamadoController {
	@Autowired
	ChamadoService chamadoService;
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ChamadoDTO chamadoDto){
		
		var chamado = new Chamado();
		
		BeanUtils.copyProperties(chamadoDto, chamado);
		
		chamado.setDataRegistro(LocalDate.now());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(chamadoService.cadastrar(chamado));
	}
	
	@GetMapping
	public ResponseEntity<List<Chamado>> getChamados(){
		return ResponseEntity.status(HttpStatus.OK).body(chamadoService.listar());
	}

}
