package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;
import com.br.projetoFinal.entity.ParametrosCarpintaria;
import com.br.projetoFinal.repository.ParametrosCarpintariaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class ParametrosCarpintariaRepositoryImpl implements ParametrosCarpintariaRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void salvarParametrosAvaliados(ParametrosCarpintariaDto parametrosCarpintariaDto) {
        em.createNativeQuery("INSERT INTO PARAMETROS_CARPINTARIA (TIPO_CARPINTARIA, ID_AVALIACAO, DIMENSOES, NIVEL_OU_PRUMO, ESTANQUEIDADE, OBS) \n" +
                    "VALUES (:TIPO_CARPINTARIA, :ID_AVALIACAO, :DIMENSOES, :NIVEL_OU_PRUMO, :ESTANQUEIDADE, :OBS)")
                .setParameter("TIPO_CARPINTARIA", parametrosCarpintariaDto.getTipoCarpintaria())
                .setParameter("ID_AVALIACAO", parametrosCarpintariaDto.getIdAvaliacao())
                .setParameter("DIMENSOES", parametrosCarpintariaDto.getDimensoes())
                .setParameter("NIVEL_OU_PRUMO", parametrosCarpintariaDto.getNivelOuPrumo())
                .setParameter("ESTANQUEIDADE", parametrosCarpintariaDto.getEstanqueidade())
                .setParameter("OBS", parametrosCarpintariaDto.getObs())
                .executeUpdate();
    }

    @Override
    public ParametrosCarpintariaDto buscarPorId(Integer idParametrosCarpintaria) {
        TypedQuery<ParametrosCarpintariaDto> query = em.createNamedQuery("ParametrosCarpintaria.buscarPorId", ParametrosCarpintariaDto.class)
                .setParameter("ID_PARAMETROS_CARPINTARIA", idParametrosCarpintaria);
        return query.getSingleResult();
    }

    @Override
    public ParametrosCarpintariaDto buscarPorAvaliacao(Integer idAvaliacao) {
        TypedQuery<ParametrosCarpintariaDto> query = em.createNamedQuery("ParametrosCarpintaria.buscarPorAvaliacao", ParametrosCarpintariaDto.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void excluirPorId(Integer idParametrosCarpintaria) {
        TypedQuery<ParametrosCarpintariaDto> query = em.createNamedQuery("ParametrosCarpintaria.excluirPorId", ParametrosCarpintariaDto.class)
                .setParameter("ID_PARAMETROS_CARPINTARIA", idParametrosCarpintaria);
    }
}
