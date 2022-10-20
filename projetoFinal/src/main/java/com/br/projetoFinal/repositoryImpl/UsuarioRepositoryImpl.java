package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public Usuario buscarPorNome(String nome) {
        TypedQuery<Usuario> query = (TypedQuery<Usuario>) getEntityManager().createNativeQuery("SELECT * FROM USUARIO WHERE NOME = :NOME")
            .setParameter("NOME", nome);
        return query.getSingleResult();
    }

    @Override
    public void salvarUsuario(UsuarioDto usuarioDto) {
            em.createNativeQuery("INSERT INTO USUARIO (LOGIN, SENHA, TIPO_USUARIO, NOME, DATA_NASCIMENTO, CPF, ENDERECO_RESIDENCIAL, " +
                            "TELEFONE, EMAIL, CONTRATANTE, DATA_ADMISSAO, DATA_DESLIGAMENTO, CARGO, REMUNERACAO) \n " +
                            "VALUES (:LOGIN, :SENHA, :TIPO_USUARIO, :NOME, :DATA_NASCIMENTO, :CPF, :ENDERECO_RESIDENCIAL, :TELEFONE, :EMAIL, " +
                            ":CONTRATANTE, :DATA_ADMISSAO, :DATA_DESLIGAMENTO, :CARGO, :REMUNERACAO)")
                    .setParameter("LOGIN", usuarioDto.getLogin())
                    .setParameter("SENHA", usuarioDto.getSenha())
                    .setParameter("TIPO_USUARIO", usuarioDto.getTipoUsuario())
                    .setParameter("NOME", usuarioDto.getNome())
                    .setParameter("DATA_NASCIMENTO", usuarioDto.getDataNascimento())
                    .setParameter("CPF", usuarioDto.getCpf())
                    .setParameter("ENDERECO_RESIDENCIAL", usuarioDto.getEnderecoResidencial())
                    .setParameter("TELEFONE", usuarioDto.getTelefone())
                    .setParameter("EMAIL", usuarioDto.getEmail())
                    .setParameter("CONTRATANTE", usuarioDto.getContratante())
                    .setParameter("DATA_ADMISSAO", usuarioDto.getDataAdmissao())
                    .setParameter("DATA_DESLIGAMENTO", usuarioDto.getDataDesligamento())
                    .setParameter("CARGO", usuarioDto.getCargo())
                    .setParameter("REMUNERACAO", usuarioDto.getRemuneracao())
                    .executeUpdate();
    }

    @Override
    public List<Usuario> listar() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM USUARIO \n" +
                "ORDER BY NOME");
        return query.getResultList();
    }

    @Override
    public Usuario buscarPorId(Integer idUsuario) {
        TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.buscarPorId", Usuario.class)
                .setParameter("ID", idUsuario);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer idUsuario) {
        em.createNativeQuery("DELETE * FROM USUARIO \n" +
                        "WHERE ID = :ID")
                .setParameter("ID", idUsuario);
    }


}
