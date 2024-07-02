package com.paces.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.paces.domain.BloqueadoLogin;


@Repository
public interface BloqueadoLoginRepository extends JpaRepository<BloqueadoLogin, Integer>{

	@Transactional(readOnly=true)
	List<BloqueadoLogin> findByUsuario(String usuario);
	
	
}
