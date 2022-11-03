package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.entity.TipoServico;
import com.br.projetoFinal.repository.TipoServicoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TipoServicoRepositoryImpl implements TipoServicoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarNovoServico(TipoServicoDto tipoServicoDto) {
        em.createNativeQuery("INSERT INTO TIPO_SERVICO_TABLE (NOME_SERVICO, UNIDADE_MEDIDA, VALOR_UNITARIO) \n" +
                        "VALUES (:NOME_SERVICO, :UNIDADE_MEDIDA, :VALOR_UNITARIO)")
                .setParameter("NOME_SERVICO", tipoServicoDto.getNomeServico())
                .setParameter("UNIDADE_MEDIDA", tipoServicoDto.getUnidadeMedida())
                .setParameter("VALOR_UNITARIO", tipoServicoDto.getValorUnitario())
                .executeUpdate();
    }

    @Override
    public void editarServico(TipoServicoDto tipoServicoDto) {
        em.createNativeQuery("UPDATE TIPO_SERVICO_TABLE \n" +
                        "SET NOME_SERVICO = :NOME_SERVICO, UNIDADE_MEDIDA = :UNIDADE_MEDIDA, VALOR_UNITARIO = :VALOR_UNITARIO) \n" +
                        "WHERE ID = :ID")
                .setParameter("NOME_SERVICO", tipoServicoDto.getNomeServico())
                .setParameter("UNIDADE_MEDIDA", tipoServicoDto.getUnidadeMedida())
                .setParameter("VALOR_UNITARIO", tipoServicoDto.getValorUnitario())
                .executeUpdate();
    }


    @Override
    public List<TipoServico> listar() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM TIPO_SERVICO_TABLE \n" +
                "ORDER BY ID");
        return query.getResultList();
    }

    @Override
    public TipoServico buscarPorid(Integer idTipoServico) {
        TypedQuery<TipoServico> query = getEntityManager().createNamedQuery("TipoServico.buscarPorId", TipoServico.class)
                .setParameter("ID", idTipoServico);
        return query.getSingleResult();
    }

    @Override
    public void excluirServico(Integer idTipoServico) {
        em.createNativeQuery("DELETE FROM TIPO_SERVICO_TABLE \n" +
                        "WHERE ID = :ID")
                .setParameter("ID", idTipoServico);
    }

    @Override
    public List<TipoServico> buscarPorNome(String nomeServico) {
        TypedQuery<TipoServico> query = getEntityManager().createNamedQuery("TipoServico.buscarPorNome", TipoServico.class)
                .setParameter("NOME_SERVICO", nomeServico);
        return query.getResultList();
    }
}
