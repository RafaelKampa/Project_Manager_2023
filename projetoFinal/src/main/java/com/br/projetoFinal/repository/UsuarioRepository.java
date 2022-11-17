package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import java.util.List;
import java.util.Map;

public interface UsuarioRepository {

    Usuario buscarPorNome(String nome);
    void salvarUsuario(UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException;
    List<Usuario> listar();
    Usuario buscarPorId(Integer idUsuario);
    void excluirPorId(Integer idUsuario);
}
