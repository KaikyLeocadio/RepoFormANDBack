package com.paces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paces.domain.TentativaLogin;


@Repository
public interface TentativaLoginRepository extends JpaRepository<TentativaLogin, Integer>{


}
