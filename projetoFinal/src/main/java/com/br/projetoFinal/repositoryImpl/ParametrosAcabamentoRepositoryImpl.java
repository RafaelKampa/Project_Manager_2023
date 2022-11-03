package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;
import com.br.projetoFinal.entity.ParametrosAcabamento;
import com.br.projetoFinal.repository.ParametrosAcabamentoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class ParametrosAcabamentoRepositoryImpl implements ParametrosAcabamentoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarParametrosAvaliados(ParametrosAcabamentoDto parametrosAcabamentoDto) {
        em.createNativeQuery("ISERT INTO PARAMETROS_ACABAMENTO (ID_AVALIACAO, DIMENSOES, REGUAMENTO, ALISAMENTO, OBS) \n" +
                        "VALUES (:ID_AVALIACAO, :DIMENSOES, :REGUAMENTO, :ALISAMENTO, :OBS)")
                .setParameter("ID_AVALIACAO", parametrosAcabamentoDto.getIdAvaliacao())
                .setParameter("PRUMO", parametrosAcabamentoDto.getDimensoes())
                .setParameter("NIVEL", parametrosAcabamentoDto.getReguamento())
                .setParameter("ALINHAMENTO", parametrosAcabamentoDto.getAlisamento())
                .setParameter("OBS", parametrosAcabamentoDto.getObs())
                .executeUpdate();
    }

    @Override
    public ParametrosAcabamento buscarPorId(Integer idParametrosAcabamento) {
        TypedQuery<ParametrosAcabamento> query = getEntityManager().createNamedQuery("ParametrosAcabamento.buscarPorId", ParametrosAcabamento.class)
                .setParameter("ID", idParametrosAcabamento);
        return query.getSingleResult();
    }

    @Override
    public ParametrosAcabamento buscarPorAvaliacao(Integer idAvaliacao) {
        TypedQuery<ParametrosAcabamento> query = getEntityManager().createNamedQuery("ParametrosAcabamento.buscarPorAvaliacao", ParametrosAcabamento.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer idParametrosAcabamento) {
        em.createNativeQuery("DELETE FROM PARAMETROS_ACABAMENTO \n" +
                        "WHERE ID = :ID")
                .setParameter("ID", idParametrosAcabamento);
    }

}
