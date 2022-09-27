package com.br.projetoFinal.repository;

import com.br.projetoFinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface UsuarioRepository {

    Usuario buscarPorNome(String nome);
    void salvar(Usuario usuario);
    List<Usuario> listar();
    Map<Object, Object> buscarPorId(Integer id);
    void excluirPorId(Integer id);
}
