package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.repository.CentroDeCustoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CentroDeCustoRepositoryImpl implements CentroDeCustoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCustoDto) {
        em.createNativeQuery("INSERT INTO CENTRO_DE_CUSTO (ID_CENTRO_DE_CUSTO, NOME_CENTRO_DE_CUSTO, ENDERECO) \n " +
                        "VALUES (SELECT MAX(ID_CENTRO_DE_CUSTO) FROM CENTRO_DE_CUSTO + 1, :NOME_CENTRO_DE_CUSTO, :ENDERECO)")
                .setParameter("NOME_CENTRO_DE_CUSTO", centroDeCustoDto.getNomeCentroDeCusto())
                .setParameter("ENDERECO", centroDeCustoDto.getEnderecoCentroDeCusto())
                .executeUpdate();
    }

    @Override
    public List<CentroDeCusto> listarCentrosDeCusto() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM CENTRO_DE_CUSTO \n" +
                "ORDER BY NOME_CENTRO_DE_CUSTO ");
        return query.getResultList();
    }

    @Override
    public CentroDeCusto buscarPorId(Integer idCentroDeCusto) {
        TypedQuery<CentroDeCusto> query = getEntityManager().createNamedQuery("CentroDeCusto.buscarPorId", CentroDeCusto.class)
                .setParameter("ID_CENTRO_DE_CUSTO", idCentroDeCusto);
        return query.getSingleResult();
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
