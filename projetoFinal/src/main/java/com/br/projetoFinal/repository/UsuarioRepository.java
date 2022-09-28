package com.br.projetoFinal.repository;

import com.br.projetoFinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface UsuarioRepository {

    List<Usuario> buscarPorNome(String nome);
    void salvar(Usuario usuario);
    List<Usuario> listar();
    List<Usuario> buscarPorId(Integer id);
    void excluirPorId(Integer id);
}
