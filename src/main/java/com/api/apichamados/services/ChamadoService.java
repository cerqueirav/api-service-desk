package com.api.apichamados.services;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apichamados.dtos.ChamadoDTO;
import com.api.apichamados.models.Chamado;
import com.api.apichamados.repositories.ChamadoRepository;

@Service
public class ChamadoService {
	@Autowired
	ChamadoRepository chamadoRepository;
	
	public List<Chamado> listar(){
		return chamadoRepository.findAll();
	}
	
	@Transactional
	public Chamado cadastrar(Chamado chamado){
		return chamadoRepository.save(chamado);
	}

}
