package com.paces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.paces.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	@Transactional(readOnly=true)
    Optional<Usuario> findByEmail(String email);

    @Transactional(readOnly=true)
    Optional<Usuario> findByEmailAndStatus(String email, int status);
   
}
