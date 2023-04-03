package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.repository.CentroDeCustoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CentroDeCustoRepositoryImpl implements CentroDeCustoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCustoDto) {
        em.createNativeQuery("INSERT INTO CENTRO_DE_CUSTO (NOME_CENTRO_DE_CUSTO, ENDERECO, VALOR_EMPREENDIDO) \n " +
                        "VALUES (:NOME_CENTRO_DE_CUSTO, :ENDERECO, :VALOR_EMPREENDIDO)")
                .setParameter("NOME_CENTRO_DE_CUSTO", centroDeCustoDto.getNomeCentroDeCusto())
                .setParameter("ENDERECO", centroDeCustoDto.getEnderecoCentroDeCusto())
                .setParameter("VALOR_EMPREENDIDO", 0.0)
                .executeUpdate();
    }

    @Override
    public List<CentroDeCustoDto> listarCentrosDeCusto() {
        TypedQuery<CentroDeCustoDto> query = em.createNamedQuery("CentroDeCusto.listarCentrosDeCusto", CentroDeCustoDto.class);
        List<CentroDeCustoDto> centro = query.getResultList();
        return centro;
    }

    @Override
    public CentroDeCusto buscarCentroPorId(Integer idCentroDeCusto) {
        TypedQuery<CentroDeCusto> query = em.createNamedQuery("CentroDeCusto.buscarCentroPorId", CentroDeCusto.class)
                .setParameter("ID_CENTRO_DE_CUSTO", idCentroDeCusto);
        CentroDeCusto centro = query.getSingleResult();
        return centro;
    }

    @Override
    public CentroDeCusto buscarPorNome(String nomeCentroDeCusto) {
        TypedQuery<CentroDeCusto> query = (TypedQuery<CentroDeCusto>) getEntityManager().createNativeQuery("SELECT * FROM CENTRO_DE_CUSTO WHERE NOME_CENTRO_DE_CUSTO = :NOME_CENTRO_DE_CUSTO")
                .setParameter("NOME_CENTRO_DE_CUSTO", nomeCentroDeCusto);
        return query.getSingleResult();
    }

    @Override
    public void excluir(Integer idCentroDeCusto) {
        em.createNativeQuery("DELETE FROM CENTRO_DE_CUSTO \n" +
                        "WHERE ID_CENTRO_DE_CUSTO = :ID_CENTRO_DE_CUSTO")
                .setParameter("ID_CENTRO_DE_CUSTO", idCentroDeCusto);
    }
}
