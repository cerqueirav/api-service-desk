package com.api.servicedesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.servicedesk.models.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>{
	@Transactional(readOnly=true)
	@Query("SELECT c FROM Cliente c WHERE c.cnpj = :cnpj")
	Optional<Solicitacao> findByClienteCnpj(String cnpj);
}
