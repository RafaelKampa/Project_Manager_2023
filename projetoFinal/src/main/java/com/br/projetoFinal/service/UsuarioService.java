package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import java.util.List;

public interface UsuarioService {

    void salvar(Usuario usuario) throws ExcecaoExemplo;
    List<Usuario> listar();
    UsuarioDto buscarPorId(Integer id);
    UsuarioDto buscarPorNome(String nome);
    void excluir(Integer id);

}
