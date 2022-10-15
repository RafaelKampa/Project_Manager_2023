package com.br.projetoFinal.repository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

public abstract class RepositorioBaseAbstract {

    public void flush() {
        getEntityManager().flush();
    }

    public void clear() {
        getEntityManager().clear();
    }

    protected boolean existe(Query query) {
        return getCountDeQuery(query) != 0;
    }

    protected Long getCountDeQuery(Query query) {
        return ((Number) query.getSingleResult()).longValue();
    }

    protected abstract EntityManager getEntityManager();

    protected StoredProcedureQuery criarStoredProcedureQuery(String nomeProcedure, Class<?>... classes) {
        return getEntityManager().createStoredProcedureQuery(nomeProcedure, classes);
    }

    protected Query criarQuery(String jpql) {
        return getEntityManager().createQuery(jpql);
    }

    protected Query criarNativeQuery(String jpql) {
        return getEntityManager().createNativeQuery(jpql);
    }

    protected <E> Query criarNativeQuery(String jpql, Class<E> classe) {
        return getEntityManager().createNativeQuery(jpql, classe);
    }

    protected Query criarNativeQuery(String qry, String resultSetMapping) {
        return getEntityManager().createNativeQuery(qry, resultSetMapping);
    }

    protected <E> TypedQuery<E> criarTypedQuery(String jpql, Class<E> classe) {
        return getEntityManager().createQuery(jpql, classe);
    }

    protected StoredProcedureQuery criarStoredProcedureQuery(String nomeProcedure) {
        return getEntityManager().createStoredProcedureQuery(nomeProcedure);
    }

    protected StoredProcedureQuery criarStoredProcedureQuery(String nomeProcedure, String... resultSetMappings) {
        return getEntityManager().createStoredProcedureQuery(nomeProcedure, resultSetMappings);
    }

    protected <T> List<T> getResultList(TypedQuery<T> typedQuery) {
        try {
            return typedQuery.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<T>();
        }
    }

    protected  <T> T getSingleResultOrNull(Query query) {
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    protected  String getSingleResultString(Query query) {
        return getSingleResultOrNull(query) == null ? "" : getSingleResultOrNull(query).toString();
    }

    protected Long bigDecimalToLong(BigDecimal bigDecimal) {
        if (bigDecimal == null) return null;
        if (BigDecimal.valueOf(Long.MAX_VALUE).compareTo(bigDecimal) < 0)
            throw new IndexOutOfBoundsException("Número maior que o MAX_VALUE do Long");
        return bigDecimal.longValue();
    }

    protected Long bigDecimalToLong(Object bigDecimal) {
        if (bigDecimal == null) return null;
        return bigDecimalToLong((BigDecimal) bigDecimal);
    }

    protected <T> void persistir(T entity) {
        EntityManager em = getEntityManager();
        if (!em.contains(entity)) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        em.flush();
    }

}