package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.SystemException;
import java.util.List;

public interface UsuarioRepository{

    UsuarioDto buscarPorNome(String nomeUsuario);
    void salvarUsuario(UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException;
    List<UsuarioDto> listar();
    List<UsuarioDto> buscarConferentes();
    List<UsuarioDto> buscarExecutores();
    UsuarioDto buscarPorId(Integer idUsuario);
    void excluirPorId(Integer idUsuario);
    Integer buscarUltimoId();
    UsuarioDto buscarPorUsername(String username);
}
