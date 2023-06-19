package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;
import com.br.projetoFinal.repository.ParametrosAcabamentoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class ParametrosAcabamentoRepositoryImpl implements ParametrosAcabamentoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void salvarParametrosAvaliados(ParametrosAcabamentoDto parametrosAcabamentoDto) {
        em.createNativeQuery("INSERT INTO PARAMETROS_ACABAMENTO (ID_AVALIACAO, DIMENSOES, REGUAMENTO, ALISAMENTO, OBS) \n" +
                        "VALUES (:ID_AVALIACAO, :DIMENSOES, :REGUAMENTO, :ALISAMENTO, :OBS)")
                .setParameter("ID_AVALIACAO", parametrosAcabamentoDto.getIdAvaliacao())
                .setParameter("DIMENSOES", parametrosAcabamentoDto.getDimensoes())
                .setParameter("REGUAMENTO", parametrosAcabamentoDto.getReguamento())
                .setParameter("ALISAMENTO", parametrosAcabamentoDto.getAlisamento())
                .setParameter("OBS", parametrosAcabamentoDto.getObs())
                .executeUpdate();
    }

    @Override
    public ParametrosAcabamentoDto buscarPorId(Integer idParametrosAcabamento) {
        TypedQuery<ParametrosAcabamentoDto> query = em.createNamedQuery("ParametrosAcabamento.buscarPorId", ParametrosAcabamentoDto.class)
                .setParameter("ID_PARAMETROS_ACABAMENTO", idParametrosAcabamento);
        return query.getSingleResult();
    }

    @Override
    public ParametrosAcabamentoDto buscarPorAvaliacao(Integer idAvaliacao) {
        TypedQuery<ParametrosAcabamentoDto> query = em.createNamedQuery("ParametrosAcabamento.buscarPorAvaliacao", ParametrosAcabamentoDto.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void excluirPorId(Integer idParametrosAcabamento) {
        em.createNamedQuery("ParametrosAcabamento.excluirPorId", ParametrosAcabamentoDto.class)
                .setParameter("ID_PARAMETROS_ACABAMENTO", idParametrosAcabamento);
    }

}
