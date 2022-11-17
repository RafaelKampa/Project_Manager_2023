package com.br.projetoFinal.repository;

import com.br.projetoFinal.entity.Login;
import com.br.projetoFinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findUserByUsername(String username);

}
