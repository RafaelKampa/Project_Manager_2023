package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDto buscarPorNome(String nome) {
        TypedQuery<UsuarioDto> query = (TypedQuery<UsuarioDto>) getEntityManager().createNativeQuery("SELECT * FROM usuario WHERE nome = :nome")
            .setParameter("nome", nome);
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
        Query query = getEntityManager().createNativeQuery("SELECT * FROM usuario");
        return query.getResultList();
    }

    @Override
    public UsuarioDto buscarPorId(Integer id) {
        TypedQuery<UsuarioDto> query = (TypedQuery<UsuarioDto>) getEntityManager().createNativeQuery("SELECT * FROM usuario WHERE id = :id")
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer id) {
        em.createNativeQuery("DELETE * FROM usuario WHERE id = :id")
                .setParameter("id", id);
    }


}
