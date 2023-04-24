package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public UsuarioDto buscarPorNome(String login) {
        TypedQuery<UsuarioDto> query = (TypedQuery<UsuarioDto>) getEntityManager().createNativeQuery("SELECT * FROM USUARIO WHERE LOGIN = :LOGIN")
            .setParameter("LOGIN", login);
        return query.getSingleResult();
    }

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void salvarUsuario(UsuarioDto usuarioDto) {
            em.createNativeQuery("INSERT INTO USUARIO (CARGO, CONTRATANTE, CPF, DATA_ADMISSAO, DATA_NASCIMENTO, EMAIL, ENDERECO_RESIDENCIAL, LOGIN, NOME, REMUNERACAO, SENHA, TELEFONE, TIPO_USUARIO)\n" +
                            "VALUES(:CARGO, :CONTRATANTE, :CPF, :DATA_ADMISSAO, :DATA_NASCIMENTO, :EMAIL, :ENDERECO_RESIDENCIAL, :LOGIN, :NOME, :REMUNERACAO, :SENHA, :TELEFONE, :TIPO_USUARIO)")
                    .setParameter("LOGIN", usuarioDto.getUsername())
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
                    .setParameter("CARGO", usuarioDto.getCargo())
                    .setParameter("REMUNERACAO", usuarioDto.getRemuneracao())
                    .executeUpdate();
    }

    @Override
    public List<UsuarioDto> listar() {
        TypedQuery<UsuarioDto> query = em.createNamedQuery("Usuario.listar", UsuarioDto.class);
        return query.getResultList();
    }

    @Override
    public List<UsuarioDto> buscarConferentes() {
        TypedQuery<UsuarioDto> query = em.createNamedQuery("Usuario.buscarConferentes", UsuarioDto.class);
        return query.getResultList();
    }

    @Override
    public List<UsuarioDto> buscarExecutores() {
        TypedQuery<UsuarioDto> query = em.createNamedQuery("Usuario.buscarExecutores", UsuarioDto.class);
        return query.getResultList();
    }

    @Override
    public UsuarioDto buscarPorId(Integer idUsuario) {
        TypedQuery<UsuarioDto> query = em.createNamedQuery("Usuario.buscarPorId", UsuarioDto.class)
                .setParameter("ID_USUARIO", idUsuario);
        return query.getSingleResult();
    }

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public void excluirPorId(Integer idUsuario) {
        TypedQuery<UsuarioDto> query = em.createNamedQuery("Usuario.excluirPorid", UsuarioDto.class)
                .setParameter("ID_USUARIO", idUsuario);
    }

}
