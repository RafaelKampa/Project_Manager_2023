package com.br.projetoFinal.repository;

import com.br.projetoFinal.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findUserByUsername(String username);

}
