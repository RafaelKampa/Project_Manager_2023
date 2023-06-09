package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.RemuneracaoDto;
import com.br.projetoFinal.repository.RemuneracaoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class RemuneracaoRepositoryImpl implements RemuneracaoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void salvarNovaRemuneracao(RemuneracaoDto remuneracaoDto) {
        em.createNativeQuery("INSERT INTO REMUNERACAO (ID_USUARIO, FUNCAO, MES_REFERENCIA, ANO_REFERENCIA, VALOR)\n" +
                        "\tVALUES (:ID_USUARIO, :FUNCAO, :MES_REFERENCIA, :ANO_REFERENCIA, :VALOR)")
                .setParameter("ID_USUARIO", remuneracaoDto.getIdUsuario())
                .setParameter("FUNCAO", remuneracaoDto.getFuncao())
                .setParameter("MES_REFERENCIA", remuneracaoDto.getMesReferencia())
                .setParameter("ANO_REFERENCIA", remuneracaoDto.getAnoReferencia())
                .setParameter("VALOR", remuneracaoDto.getValor())
                .executeUpdate();
    }

    @Override
    public Double buscarUltimaRemuneracaoUsuario(Integer idUsuario) {
        TypedQuery<Double> query = em.createNamedQuery("Remuneracao.buscarUltimaRemuneracaoUsuario", Double.class)
                .setParameter("ID_USUARIO", idUsuario);
        return query.getSingleResult();
    }
}
