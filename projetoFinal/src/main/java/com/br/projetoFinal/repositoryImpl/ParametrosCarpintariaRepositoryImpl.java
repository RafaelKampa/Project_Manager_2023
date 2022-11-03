package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;
import com.br.projetoFinal.entity.ParametrosCarpintaria;
import com.br.projetoFinal.repository.ParametrosCarpintariaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class ParametrosCarpintariaRepositoryImpl implements ParametrosCarpintariaRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarParametrosAvaliados(ParametrosCarpintariaDto parametrosCarpintariaDto) {
        em.createNativeQuery("INSERT INTO PARAMETROS_CARPINTARIA (TIPO_CARPINTARIA, ID_AVALIACAO, DIMENSOES, NIVEL_OU_PRUMO, ESTANQUEIDADE, OBS) \n" +
                    "VALUES ( :TIPO_CARPINTARIA, :ID_AVALIACAO, :DIMENSOES, :NIVEL_OU_PRUMO, :ESTANQUEIDADE, :OBS)")
                .setParameter("TIPO_CARPINTARIA", parametrosCarpintariaDto.getTipoCarpintaria())
                .setParameter("ID_AVALIACAO", parametrosCarpintariaDto.getIdAvaliacao())
                .setParameter("DIMENSOES", parametrosCarpintariaDto.getDimensoes())
                .setParameter("NIVEL_OU_PRUMO", parametrosCarpintariaDto.getNivelOuPrumo())
                .setParameter("ESTANQUEIDADE", parametrosCarpintariaDto.getEstanqueidade())
                .setParameter("OBS", parametrosCarpintariaDto.getObs())
                .executeUpdate();
    }

    @Override
    public ParametrosCarpintaria buscarPorId(Integer idParametrosCarpintaria) {
        TypedQuery<ParametrosCarpintaria> query = getEntityManager().createNamedQuery("ParametrosCarpintaria.buscarPorId", ParametrosCarpintaria.class)
                .setParameter("ID", idParametrosCarpintaria);
        return query.getSingleResult();
    }

    @Override
    public ParametrosCarpintaria buscarPorAvaliacao(Integer idAvaliacao) {
        TypedQuery<ParametrosCarpintaria> query = getEntityManager().createNamedQuery("ParametrosCarpintaria.buscarPorAvaliacao", ParametrosCarpintaria.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer idParametrosCarpintaria) {
        em.createNativeQuery("DELETE FROM PARAMETROS_CARPINTARIA \n" +
                "WHERE ID = :ID")
                .setParameter("ID", idParametrosCarpintaria);
    }
}
