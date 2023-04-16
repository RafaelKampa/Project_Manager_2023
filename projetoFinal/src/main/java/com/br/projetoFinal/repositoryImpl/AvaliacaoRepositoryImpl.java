package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.entity.Avaliacao;
import com.br.projetoFinal.repository.AvaliacaoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void avaliar(AvaliacaoDto avaliacaoDto) {
        em.createNativeQuery("INSERT INTO AVALIACAO (TIPO_SERVICO, ID_SERVICO, USU_EXECT, USU_CONF, RESULTADO, DATA_AVALIACAO, OBS)\n" +
                "\tVALUES (:TIPO_SERVICO, :ID_SERVICO, :USU_EXECT, :USU_CONF, :RESULTADO, :DATA_AVALIACAO, :OBS)")
                .setParameter("TIPO_SERVICO", avaliacaoDto.getTipoServico())
                .setParameter("ID_SERVICO", avaliacaoDto.getIdServico())
                .setParameter("USU_EXECT", avaliacaoDto.getUsuExect())
                .setParameter("USU_CONF", avaliacaoDto.getUsuConf())
                .setParameter("RESULTADO", avaliacaoDto.getResultado())
                .setParameter("DATA_AVALIACAO", avaliacaoDto.getDataAvaliacao())
                .setParameter("OBS", avaliacaoDto.getObs())
                .executeUpdate();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void reavaliar(AvaliacaoDto avaliacaoDto) {
        em.createNativeQuery("UPDATE AVALIACAO \n" +
                        "SET RESULT_REAVAL = :RESULT_REAVAL, DATA_REAVALIACAO = :DATA_REAVALIACAO, OBS = :OBS \n" +
                        "WHERE ID = :ID")
                .setParameter("ID", avaliacaoDto.getIdAvaliacao())
                .setParameter("RESULT_REAVAL", avaliacaoDto.getResultReaval())
                .setParameter("DATA_REAVALIACAO", avaliacaoDto.getDataReavaliacao())
                .setParameter("OBS", avaliacaoDto.getObs())
                .executeUpdate();
    }

    @Override
    public List<AvaliacaoDto> listar() {
        Query query = em.createNativeQuery("SELECT * FROM AVALIACAO \n" +
                "ODER BY ID");
        return query.getResultList();
    }

    @Override
    public AvaliacaoDto buscarPorId(Integer idAvaliacao) {
        TypedQuery<AvaliacaoDto> query = em.createNamedQuery("Avaliacao.buscarPorId", AvaliacaoDto.class)
                .setParameter("ID_AVALIACAO", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    public List<AvaliacaoDto> buscarPorServico(Integer tipoServico) {
        TypedQuery<AvaliacaoDto> query = em.createNamedQuery("Avaliacao.buscarPorServico", AvaliacaoDto.class)
                .setParameter("TIPO_SERVICO", tipoServico);
        return query.getResultList();
    }

    @Override
    public List<AvaliacaoDto> buscarPorExecutor(Integer idUsuExect) {
        TypedQuery<AvaliacaoDto> query = em.createNamedQuery("Avaliacao.buscarPorExecutor", AvaliacaoDto.class)
                .setParameter("ID_USU_EXECT", idUsuExect);
        return query.getResultList();
    }

    @Override
    public List<AvaliacaoDto> buscarPorConferente(Integer idUsuConf) {
        TypedQuery<AvaliacaoDto> query = em.createNamedQuery("Avaliacao.buscarPorConferente", AvaliacaoDto.class)
                .setParameter("ID_USU_CONF", idUsuConf);
        return query.getResultList();
    }
}
