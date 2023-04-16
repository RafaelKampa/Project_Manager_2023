package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import javax.transaction.SystemException;
import java.util.List;

public interface UsuarioRepository{

    Usuario buscarPorNome(String login);
    void salvarUsuario(UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException;
    List<Usuario> listar();
    List<Usuario> buscarConferentes();
    List<Usuario> buscarExecutores();
    Usuario buscarPorId(Integer idUsuario);
    void excluirPorId(Integer idUsuario);
}
