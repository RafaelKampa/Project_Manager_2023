package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.repository.ParametrosAlvenariaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

@Repository
public class ParametrosAlvenariaRepositoryImpl implements ParametrosAlvenariaRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
}
