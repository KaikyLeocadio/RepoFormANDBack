package com.paces.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paces.domain.Usuario;
import com.paces.dto.UsuarioDTO;
import com.paces.dto.UsuarioDTOAtualizar;
import com.paces.dto.UsuarioDTOCadastrar;
import com.paces.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository basededados;
	@Autowired
	BCryptPasswordEncoder pe;
	// estrutura de um metodo: public / private, tipo do return, nome do metodo, parametros sempre dentro de (), {}
	// estrutura do parametro: tipo do parametro, nome do parametro
	// exemplo: Integer Id, Integer eh o tipo do parametro e Id eh o nome dele.
public Usuario getById (Integer Id) {
	return basededados.findById(Id).get();	
}
	
	public Usuario cadastro (Usuario ob) {
		return basededados.save(ob);
		
	}
	// instanciar um objeto eh: gerar um espaço em memoria para salvar um tipo de informação
	// instanciar um objeto eh: dar vida a abstração que eh a classe
	// como instanciar um objeto: Usuario obj = new Usuario();
	// new Usuario(); eh a forma como a linguagem de programação chama o construtor da classe e aloca em espaço em memoria.
	public Usuario converter(UsuarioDTO objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Usuario novo = new Usuario();
		
		try {
			novo.setDatadenascimento(sf.parse(objDTO.getDatadenascimento()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setEmail(objDTO.getEmail());
		novo.setSenha(pe.encode(objDTO.getSenha()));
		novo.setDocumento(objDTO.getDocumento());
		novo.setNomecompleto(objDTO.getNomecompleto());
		novo.setNomeocial(objDTO.getNomeocial());
		novo.setGenero(objDTO.getGenero());
		novo.setTipodedocumento(objDTO.getTipodedocumento());
		
		
		return novo;
	}
	public Usuario converter(UsuarioDTOCadastrar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Usuario novo = new Usuario();
		
		try {
			novo.setDatadenascimento(sf.parse(objDTO.getDatadenascimento()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setEmail(objDTO.getEmail());
		novo.setSenha(pe.encode(objDTO.getSenha()));
		novo.setDocumento(objDTO.getDocumento());
		novo.setNomecompleto(objDTO.getNomecompleto());
		novo.setNomeocial(objDTO.getNomeocial());
		novo.setGenero(objDTO.getGenero());
		novo.setTipodedocumento(objDTO.getTipodedocumento());
		
		return novo;
	}	
	
	public Usuario converter(UsuarioDTOAtualizar objDTO) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Usuario novo = new Usuario();
		
		try {
			novo.setDatadenascimento(sf.parse(objDTO.getDatadenascimento()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		novo.setEmail(objDTO.getEmail());
		novo.setSenha(pe.encode(objDTO.getSenha()));
		novo.setDocumento(objDTO.getDocumento());
		novo.setNomecompleto(objDTO.getNomecompleto());
		novo.setNomeocial(objDTO.getNomeocial());
		novo.setGenero(objDTO.getGenero());
		novo.setTipodedocumento(objDTO.getTipodedocumento());
		
		return novo;
	}	
	
	public List<Usuario> buscartodos(){
		return basededados.findAll();
	}
	public Usuario atualizar(Usuario novo) {
		Usuario antigo = this.getById(novo.getId());
		this.updatedata(novo, antigo);
		return this.basededados.save(antigo);
	}
	
	public void updatedata(Usuario novo,Usuario antigo) {
		antigo.setEmail(novo.getEmail());
		antigo.setNomecompleto(novo.getNomecompleto());
		antigo.setStatus(novo.getStatus());
		
	}
	//APAGUE ABAIXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
	public Usuario login(String email, String senha) {
        Optional<Usuario> optUsuario = basededados.findByEmail(email);
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            if (pe.matches(senha, usuario.getSenha())) {
                return usuario;
            }
        }
        return null; // Retorna null se o usuário não for encontrado ou a senha estiver incorreta
	}

	public Usuario findByEmail(String email) {
	    Optional<Usuario> optUsuario = basededados.findByEmail(email);
	    return optUsuario.orElse(null); // Retorna o usuário encontrado ou null se não encontrado
	}

	public boolean checkPassword(Usuario usuario, String senha) {
	    return pe.matches(senha, usuario.getSenha());
	}

}
