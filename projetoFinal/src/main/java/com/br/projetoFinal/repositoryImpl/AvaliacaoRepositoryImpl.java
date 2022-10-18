package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.repository.AvaliacaoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

@Repository
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }


}
