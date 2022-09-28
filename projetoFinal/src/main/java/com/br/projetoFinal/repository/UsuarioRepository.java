package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface UsuarioRepository {

    UsuarioDto buscarPorNome(String nome);
    void salvar(Usuario usuario) throws ExcecaoExemplo;
    List<Usuario> listar();
    UsuarioDto buscarPorId(Integer id);
    void excluirPorId(Integer id);
}
