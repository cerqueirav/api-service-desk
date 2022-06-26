package com.api.servicedesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.servicedesk.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
