package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ParametrosFerragemDto;
import com.br.projetoFinal.entity.ParametrosFerragem;
import com.br.projetoFinal.repository.ParametrosFerragemRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class ParametrosFerragemRepositoryImpl implements ParametrosFerragemRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarParametrosAvaliados(ParametrosFerragemDto parametrosFerragemDto) {
        em.createNativeQuery("INSERT INTO PARAMETROS_FERRAGEM (ID_AVALIACAO, ESPACAMENTO, QTDE_ACO, DISTRIBUICAO, OBS) \n" +
                        "VALUES (:ID_AVALIACAO, :ESPACAMENTO, :QTDE_ACO, :DISTRIBUICAO, :OBS)")
                .setParameter("ID_AVALIACAO", parametrosFerragemDto.getIdAvaliacao())
                .setParameter("ESPACAMENTO", parametrosFerragemDto.getEspacamento())
                .setParameter("QTDE_ACO", parametrosFerragemDto.getQtdeAco())
                .setParameter("DISTRIBUICAO", parametrosFerragemDto.getDistribuicao())
                .setParameter("OBS", parametrosFerragemDto.getObs())
                .executeUpdate();
    }

    @Override
    public ParametrosFerragem buscarPorId(Integer idParametrosFerragem) {
        TypedQuery<ParametrosFerragem> query = getEntityManager().createNamedQuery("ParametrosFerragem.buscarPorId", ParametrosFerragem.class)
                .setParameter("ID", idParametrosFerragem);
        return query.getSingleResult();
    }

    @Override
    public ParametrosFerragem buscarPorAvaliacao(Integer idAvaliacao) {
        TypedQuery<ParametrosFerragem> query = getEntityManager().createNamedQuery("ParametrosFerragem.buscarPorAvaliacao", ParametrosFerragem.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer idParametrosFerragem) {
        em.createNativeQuery("DELETE FROM PARAMETROS_FERRAGEM \n" +
                        "WHERE ID = :ID")
                .setParameter("ID", idParametrosFerragem);
    }
}
