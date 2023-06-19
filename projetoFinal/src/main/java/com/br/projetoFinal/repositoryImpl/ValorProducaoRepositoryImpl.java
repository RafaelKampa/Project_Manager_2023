package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ValorProducaoDto;
import com.br.projetoFinal.repository.ValorProducaoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class ValorProducaoRepositoryImpl implements ValorProducaoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void inserirValorProducao(ValorProducaoDto valorProducaoDto) {
        em.createNativeQuery("INSERT INTO VALOR_PRODUCAO (ID_SERVICO, ID_AVALIACAO, ID_CENTRO_DE_CUSTO, ID_USUARIO, MES_REFERENCIA, ANO_REFERENCIA, VALOR_SERVICO)\n" +
                        "\tVALUES (:ID_SERVICO, :ID_AVALIACAO, :ID_CENTRO_DE_CUSTO, :ID_USUARIO, :MES_REFERENCIA, :ANO_REFERENCIA, :VALOR_SERVICO)")
                .setParameter("ID_SERVICO", valorProducaoDto.getIdServico())
                .setParameter("ID_AVALIACAO", valorProducaoDto.getIdAvaliacao())
                .setParameter("ID_CENTRO_DE_CUSTO", valorProducaoDto.getIdCentroDeCusto())
                .setParameter("ID_USUARIO", valorProducaoDto.getIdUsuario())
                .setParameter("MES_REFERENCIA", valorProducaoDto.getMesReferencia())
                .setParameter("ANO_REFERENCIA", valorProducaoDto.getAnoReferencia())
                .setParameter("VALOR_SERVICO", valorProducaoDto.getValorServico())
                .executeUpdate();
    }

    @Override
    public Double buscarValorMensal(Integer idUsuario, Integer mesReferencia, Integer anoReferencia) {
        TypedQuery<Double> query = em.createNamedQuery("ValorProducao.buscarValorMensal", Double.class)
                .setParameter("ID_USUARIO", idUsuario)
                .setParameter("MES_REFERENCIA", mesReferencia)
                .setParameter("ANO_REFERENCIA", anoReferencia);
        return query.getSingleResult();
    }
}
