package com.br.projetoFinal.service;

import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import java.util.List;

public interface UsuarioService {

    public void salvar(Usuario usuario) throws ExcecaoExemplo;
    public List<Usuario> listar();
    public List<Usuario> buscarPorId(Integer id);
    public List<Usuario> buscarPorNome(String nome);
    public void excluir(Integer id);

}
