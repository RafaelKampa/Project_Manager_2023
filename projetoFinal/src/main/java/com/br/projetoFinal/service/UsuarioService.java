package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface UsuarioService {
    void salvarUsuario(UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException;
    List<UsuarioDto> listar();
    List<UsuarioDto> buscarConferentes();
    List<UsuarioDto> buscarExecutores();
    UsuarioDto buscarPorId(Integer idUsuario);
    UsuarioDto buscarPorNome(String login);
    void excluir(Integer idUsuario) throws SystemException;
    Integer buscarUltimoId();
}
