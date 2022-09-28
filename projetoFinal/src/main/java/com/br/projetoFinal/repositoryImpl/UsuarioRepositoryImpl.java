package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.*;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDto buscarPorNome(String nome) {
        TypedQuery<UsuarioDto> query = getEntityManager().createNamedQuery("SELECT * FROM usuario WHERE nome = :nome", UsuarioDto.class);
        query.setParameter("id", nome);
        return query.getSingleResult();
    }

    @Override
    public void salvarUsuario(UsuarioDto usuarioDto) {
            em.createNativeQuery("INSERT INTO usuario (id, login, senha, tipoUsuario, nome, dataNascimento, cpf, enderecoResidencial, " +
                            "telefone, email, contratante, dataAdmissao, dataDesligamento, cargo, remuneracao) " +
                            "VALUES (:id, :login, :senha, :tipoUsuario, :nome, :dataNascimento, :cpf, :enderecoResidencial, :telefone, :email, " +
                            ":contratante, :dataAdmissao, :dataDesligamento, :cargo, :remuneracao)")
                    .setParameter("id", usuarioDto.getId())
                    .setParameter("login", usuarioDto.getLogin())
                    .setParameter("senha", usuarioDto.getSenha())
                    .setParameter("tipoUsuario", usuarioDto.getTipoUsuario())
                    .setParameter("nome", usuarioDto.getNome())
                    .setParameter("dataNascimento", usuarioDto.getDataNascimento())
                    .setParameter("cpf", usuarioDto.getCpf())
                    .setParameter("enderecoResidencial", usuarioDto.getEnderecoResidencial())
                    .setParameter("telefone", usuarioDto.getTelefone())
                    .setParameter("email", usuarioDto.getEmail())
                    .setParameter("contratante", usuarioDto.getContratante())
                    .setParameter("dataAdmissao", usuarioDto.getDataAdmissao())
                    .setParameter("dataDesligamento", usuarioDto.getDataDesligamento())
                    .setParameter("cargo", usuarioDto.getCargo())
                    .setParameter("remuneracao", usuarioDto.getRemuneracao())
                    .executeUpdate();
    }

    @Override
    public List<Usuario> listar() {
        Query query = getEntityManager().createNamedQuery("SELECT * FROM usuario");
        return query.getResultList();
    }

    @Override
    public UsuarioDto buscarPorId(Integer id) {
        TypedQuery<UsuarioDto> query = getEntityManager().createNamedQuery("SELECT * FROM usuario WHERE id = :id", UsuarioDto.class);
                query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer id) {
        em.createNamedQuery("DELETE * FROM usuario WHERE id = :id")
                .setParameter("id", id);
    }


}
