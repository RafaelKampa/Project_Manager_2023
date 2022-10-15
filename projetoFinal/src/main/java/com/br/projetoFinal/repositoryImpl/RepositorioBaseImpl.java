package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.repository.RepositorioBaseAbstract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class RepositorioBaseImpl extends RepositorioBaseAbstract {

    @Inject
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    protected Double converterBigDecimalToDouble(BigDecimal valor) {
        if (valor == null) {
            return null;
        }
        if (BigDecimal.valueOf(Integer.MAX_VALUE).compareTo(valor) < 0) {
            throw new IndexOutOfBoundsException("Número maior que o MAX_VALUE do Double");
        }
        return valor.doubleValue();
    }

    protected Long converterBigDecimalToLong(BigDecimal bigDecimal) {
        if (bigDecimal == null)
            return null;
        if (BigDecimal.valueOf(Long.MAX_VALUE).compareTo(bigDecimal) < 0)
            throw new IndexOutOfBoundsException("Número maior que o MAX_VALUE do Long");
        return bigDecimal.longValue();
    }

    protected Long converterBigDecimalToLong(Object bigDecimal) {
        if (bigDecimal == null)
            return null;
        return converterBigDecimalToLong((BigDecimal) bigDecimal);
    }

    protected Integer converterBigDecimalToInteger(BigDecimal valor) {
        if (valor == null) {
            return null;
        }
        if (BigDecimal.valueOf(Integer.MAX_VALUE).compareTo(valor) < 0) {
            throw new IndexOutOfBoundsException("Número maior que o MAX_VALUE do Integer");
        }
        return valor.intValue();
    }

    public Date getDataHoraServidor() {
        Query query = getEntityManager().createNativeQuery("SELECT SYSDATE FROM DUAL");
        return Date.class.cast(query.getSingleResult());
    }

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return getEntityManager().find(entityClass, primaryKey);
    }

    public static <E> List<E> castCollection(Collection<?> collection, Class<? extends E> clazz) {
        List<E> result = new ArrayList<E>(collection.size());
        collection.forEach(obj -> result.add(clazz.cast(obj)));
        return result;
    }

}