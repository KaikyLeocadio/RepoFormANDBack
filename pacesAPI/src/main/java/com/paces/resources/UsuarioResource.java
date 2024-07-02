package com.paces.resources;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paces.domain.Usuario;
import com.paces.dto.UsuarioDTO;
import com.paces.dto.UsuarioDTOLogin;
import com.paces.dto.UsuarioDTOCadastrar;
import com.paces.services.UserService;
import com.paces.services.UsuarioService;

//CONTROLLER

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {
	@Autowired
	private UsuarioService services;


	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> find(@PathVariable Integer id) {
		Usuario obj = services.getById(id);
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/logado", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> findLogado() {
		Usuario logado = services.getById(UserService.authenticated().getId());
		return ResponseEntity.ok().body(new UsuarioDTO(logado));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/registrar", method = RequestMethod.POST) // value = altera a url
	public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioDTOCadastrar objDTO) {
		Usuario obj = services.converter(objDTO);
		obj = services.cadastro(obj);

		
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscartodos() {
		return ResponseEntity.ok().body(services.buscartodos());
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@RequestBody UsuarioDTO objDTO,@PathVariable Integer id) {
		Usuario obj = services.converter(objDTO);
		obj.setId(id);
		obj = services.atualizar(obj);
		return ResponseEntity.ok().body(new UsuarioDTO(obj));
	}
	
	//APAGUE ABAIXOOOOOOOOOOOOOOOO
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UsuarioDTOLogin loginDTO) {
        Usuario usuario = services.findByEmail(loginDTO.getEmail());
        
        if (usuario != null && services.checkPassword(usuario, loginDTO.getSenha())) {
            // Aqui você pode retornar qualquer informação adicional que precisa
            return ResponseEntity.ok().body("Login realizado com sucesso para: " + usuario.getEmail());
        } else {
            // Retornar uma resposta indicando que o login falhou
            return ResponseEntity.badRequest().body("Email ou senha incorretos");
        }
    }
	
}
