package com.paces.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.paces.domain.Usuario;
import com.paces.dto.UsuarioDTOAtualizar;
import com.paces.excpetions.AuthorizationException;
import com.paces.excpetions.FieldMessage;
import com.paces.repositories.UsuarioRepository;
import com.paces.security.UserSS;
import com.paces.services.UserService;
import com.paces.services.UsuarioService;
import com.paces.validations.UsuarioAtualizar;

public class UsuarioAtualizarValidator implements ConstraintValidator<UsuarioAtualizar, UsuarioDTOAtualizar> {

	@Autowired
	UsuarioService service;
	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioAtualizar ann) {
	}

	@Override
	public boolean isValid(UsuarioDTOAtualizar objDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		

		if (objDTO.getEmail().equals("")) {
			list.add(new FieldMessage("email", "É Necessário digitar o usuário!"));
		} else {
			Optional<Usuario> usuario = repo.findByEmail(objDTO.getEmail());
			if(usuario.isPresent()) {
				if(usuario.get().getId() != UserService.authenticated().getId()){
					list.add(new FieldMessage("email", "Email já cadastrado utilize outro nome de usuário!"));
				}
			}
		}
		if (objDTO.getNomecompleto().equals("")) {
			list.add(new FieldMessage("nome", "É Necessário digitar o nome do usuário!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();

	}
}
