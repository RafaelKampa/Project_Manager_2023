package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ParametrosAlvenariaDto;
import com.br.projetoFinal.entity.ParametrosAlvenaria;
import com.br.projetoFinal.repository.ParametrosAlvenariaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class ParametrosAlvenariaRepositoryImpl implements ParametrosAlvenariaRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void salvarParametrosAvaliados(ParametrosAlvenariaDto parametrosAlvenariaDto) {
        em.createNativeQuery("ISERT INTO PARAMETROS_ALVENARIA (ID_AVALIACAO, PRUMO, NIVEL, ALINHAMENTO, DIMENSOES, INTEGRIDADE, LIMPEZA, OBS) \n" +
                "VALUES (:ID_AVALIACAO, :PRUMO, :NIVEL, :ALINHAMENTO, : DIMENSOES, :INTEGRIDADE, :LIMPEZA, :OBS)")
                .setParameter("ID_AVALIACAO", parametrosAlvenariaDto.getIdAvaliacao())
                .setParameter("PRUMO", parametrosAlvenariaDto.getPrumo())
                .setParameter("NIVEL", parametrosAlvenariaDto.getNivel())
                .setParameter("ALINHAMENTO", parametrosAlvenariaDto.getAlinhamento())
                .setParameter("DIMENSOES", parametrosAlvenariaDto.getDimensoes())
                .setParameter("INTEGRIDADE", parametrosAlvenariaDto.getIntegridade())
                .setParameter("LIMPEZA", parametrosAlvenariaDto.getLimpeza())
                .setParameter("OBS", parametrosAlvenariaDto.getObs())
                .executeUpdate();
    }

    @Override
    public ParametrosAlvenaria buscarPorId(Integer idParametrosAlvenaria) {
        TypedQuery<ParametrosAlvenaria> query = getEntityManager().createNamedQuery("ParametrosAlvenaria.buscarPorId", ParametrosAlvenaria.class)
                .setParameter("ID", idParametrosAlvenaria);
        return query.getSingleResult();
    }

    @Override
    public ParametrosAlvenaria buscarPorAvaliacao(Integer idAvaliacao) {
        TypedQuery<ParametrosAlvenaria> query = getEntityManager().createNamedQuery("ParametrosAlvenaria.buscarPorAvaliacao", ParametrosAlvenaria.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer idParametrosAlvenaria) {
        em.createNativeQuery("DELETE FROM PARAMETROS_ALVENARIA \n" +
                        "WHERE ID = :ID")
                .setParameter("ID", idParametrosAlvenaria);
    }

}
