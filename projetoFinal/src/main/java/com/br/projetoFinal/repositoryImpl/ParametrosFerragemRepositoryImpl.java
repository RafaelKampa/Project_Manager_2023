package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ParametrosFerragemDto;
import com.br.projetoFinal.repository.ParametrosFerragemRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class ParametrosFerragemRepositoryImpl implements ParametrosFerragemRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
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
    public ParametrosFerragemDto buscarPorId(Integer idParametrosFerragem) {
        TypedQuery<ParametrosFerragemDto> query = em.createNamedQuery("ParametrosFerragem.buscarPorId", ParametrosFerragemDto.class)
                .setParameter("ID_PARAMETROS_FERRAGEM", idParametrosFerragem);
        return query.getSingleResult();
    }

    @Override
    public ParametrosFerragemDto buscarPorAvaliacao(Integer idAvaliacao) {
        TypedQuery<ParametrosFerragemDto> query = getEntityManager().createNamedQuery("ParametrosFerragem.buscarPorAvaliacao", ParametrosFerragemDto.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void excluirPorId(Integer idParametrosFerragem) {
        TypedQuery<ParametrosFerragemDto> query = getEntityManager().createNamedQuery("ParametrosFerragem.excluirPorId", ParametrosFerragemDto.class)
                .setParameter("ID_PARAMETROS_FERRAGEM", idParametrosFerragem);
    }
}
