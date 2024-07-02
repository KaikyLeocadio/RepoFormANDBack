package com.paces.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.paces.domain.Usuario;
import com.paces.dto.UsuarioDTOCadastrar;
import com.paces.excpetions.FieldMessage;
import com.paces.repositories.UsuarioRepository;
import com.paces.services.UsuarioService;
import com.paces.validations.UsuarioCadastrar;

public class UsuarioCadastrarValidator implements ConstraintValidator<UsuarioCadastrar, UsuarioDTOCadastrar> {

	@Autowired
	UsuarioService service;
	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioCadastrar ann) {
	}

	@Override
	public boolean isValid(UsuarioDTOCadastrar objDto, ConstraintValidatorContext context) {
	    List<FieldMessage> list = new ArrayList<>();

	    if (objDto == null) {
	        // Exemplo de tratamento para objeto nulo
	        list.add(new FieldMessage("objDto", "O objeto de dados do usuário está nulo."));
	        return false;
	    }

	    if (objDto.getEmail() == null || objDto.getEmail().isEmpty()) {
	        list.add(new FieldMessage("email", "É necessário digitar o email do usuário!"));
	    } else {
	        Optional<Usuario> existingUser = repo.findByEmail(objDto.getEmail());
	        if (existingUser.isPresent()) {
	            list.add(new FieldMessage("email", "Usuário já cadastrado. Utilize outro email."));
	        }
	    }

	    // Outras validações de campos como senha, nome completo, etc.

	    for (FieldMessage e : list) {
	        context.disableDefaultConstraintViolation();
	        context.buildConstraintViolationWithTemplate(e.getMessage())
	               .addPropertyNode(e.getFieldName())
	               .addConstraintViolation();
	    }

	    return list.isEmpty();
	}


}

