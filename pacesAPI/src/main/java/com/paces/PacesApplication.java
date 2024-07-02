package com.paces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.paces.domain.Usuario;
import com.paces.repositories.UsuarioRepository;

@SpringBootApplication
public class PacesApplication implements CommandLineRunner {

	@Autowired
    private UsuarioRepository userRepository;
	@Autowired
	BCryptPasswordEncoder pe;
	
	public static void main(String[] args) {
		SpringApplication.run(PacesApplication.class, args);
	}
	public void run(String... args) throws Exception {
	Usuario obj = new Usuario();
	
/*	obj.setEmail("desgraca@gmail.com");
	obj.setSenha(pe.encode("123"));
	obj.setStatus(1);
	userRepository.save(obj); */
		
	}
}
