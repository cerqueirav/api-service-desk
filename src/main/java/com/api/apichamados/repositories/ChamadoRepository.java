package com.api.apichamados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apichamados.models.Chamado;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long>{
	boolean existsById(Long id);
}
