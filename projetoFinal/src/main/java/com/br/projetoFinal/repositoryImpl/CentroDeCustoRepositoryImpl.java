package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.CentroDeCustoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class CentroDeCustoRepositoryImpl implements CentroDeCustoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCustoDto) {
        em.createNativeQuery("INSERT INTO CENTRO_DE_CUSTO (NOME_CENTRO_DE_CUSTO, ENDERECO) \n " +
                        "VALUES (:NOME_CENTRO_DE_CUSTO, :ENDERECO)")
                .setParameter("NOME_CENTRO_DE_CUSTO", centroDeCustoDto.getNomeCentroDeCusto())
                .setParameter("ENDERECO", centroDeCustoDto.getEnderecoCentroDeCusto())
                .executeUpdate();
    }

    @Override
    public CentroDeCusto buscarPorId(Integer idCentroDeCusto) {
        TypedQuery<CentroDeCusto> query = getEntityManager().createNamedQuery("CentroDeCusto.buscarPorId", CentroDeCusto.class)
                .setParameter("ID", idCentroDeCusto);
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
                        "WHERE ID = :ID")
                .setParameter("ID", idCentroDeCusto);
    }
}
