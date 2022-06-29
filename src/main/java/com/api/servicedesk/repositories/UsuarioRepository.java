package com.api.servicedesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.api.servicedesk.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	@Transactional(readOnly=true)
	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	Optional<Usuario> findByEmail(String email);
}
