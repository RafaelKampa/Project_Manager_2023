package com.br.projetoFinal.service;

import com.br.projetoFinal.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public void salvar(Usuario usuario);
    public List<Usuario> listar();
    public Usuario buscarPorId(Integer id);
    public Usuario buscarPorNome(String nome);
    public void excluir(Integer id);

}
