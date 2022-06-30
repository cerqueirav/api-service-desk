package com.api.servicedesk.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.api.servicedesk.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	@Transactional(readOnly=true)
	@Query("SELECT c FROM Cliente c WHERE c.id = :id")
	Optional<Cliente> findById(Long id);
	
	@Transactional(readOnly=true)
	@Query("SELECT c FROM Cliente c WHERE c.cnpj = :cnpj")
	Cliente findByCnpj(String cnpj);
	
	@Transactional(readOnly=true)
	@Query("SELECT c FROM Cliente c WHERE c.endereco = :endereco")
	Optional<Cliente> findByEndereco(String endereco);
	
	public Page<Cliente> findByNome(String nome, Pageable pageable);
}
