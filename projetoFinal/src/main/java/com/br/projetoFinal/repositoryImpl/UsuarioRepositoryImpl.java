package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Inject
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Usuario> buscarPorNome(String nome) {
        Query query = getEntityManager().createNamedQuery("SELECT * FROM usuario WHERE nome = :nome")
                .setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public void salvar(Usuario usuario) {
        em.createNamedQuery("INSERT INTO usuario (id, login, senha, tipoUsuario, nome, dataNascimento, cpf, enderecoResidencial, " +
                    "telefone, email, contratante, dataAdmissao, dataDesligamento, cargo, remuneracao) " +
                    "VALUES (:id, :login, :senha, :tipoUsuario, :nome, :dataNascimento, :cpf, :enderecoResidencial, :telefone, :email, " +
                    ":contratante, :dataAdmissao, :dataDesligamento, :cargo, :remuneracao)")
                .setParameter("id", usuario.getId())
                .setParameter("login", usuario.getLogin())
                .setParameter("senha", usuario.getSenha())
                .setParameter("tipoUsuario", usuario.getTipoUsuario())
                .setParameter("nome", usuario.getNome())
                .setParameter("dataNascimento", usuario.getDataNascimento())
                .setParameter("cpf", usuario.getCpf())
                .setParameter("enderecoResidencial", usuario.getEnderecoResidencial())
                .setParameter("telefone", usuario.getTelefone())
                .setParameter("email", usuario.getEmail())
                .setParameter("contratante", usuario.getContratante())
                .setParameter("dataAdmissao", usuario.getDataAdmissao())
                .setParameter("dataDesligamento", usuario.getDataDesligamento())
                .setParameter("cargo", usuario.getCargo())
                .setParameter("remuneracao", usuario.getRemuneracao())
                .executeUpdate();
    }

    @Override
    public List<Usuario> listar() {
        Query query = getEntityManager().createNamedQuery("SELECT * FROM usuario");
        return query.getResultList();
    }

    @Override
    public List<Usuario> buscarPorId(Integer id) {
        Query query = getEntityManager().createNamedQuery("SELECT * FROM usuario WHERE id = :id")
                .setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void excluirPorId(Integer id) {
        em.createNamedQuery("DELETE * FROM usuario WHERE id = :id")
                .setParameter("id", id);
    }


}
