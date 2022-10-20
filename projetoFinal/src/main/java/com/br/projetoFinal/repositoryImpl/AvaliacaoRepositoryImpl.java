package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.entity.Avaliacao;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.repository.AvaliacaoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void avaliar(AvaliacaoDto avaliacaoDto) {
        em.createNativeQuery("INSERT INTO AVALIACAO (TIPO_SERVICO, ID_SERVICO, ID_USU_EXECT, ID_USU_CONF, RESULTADO, DATA_AVALIACAO, OBS)\n" +
                "\tVALUES (:TIPO_SERVICO, :ID_SERVICO, :ID_USU_EXECT, :ID_USU_CONF, :RESULTADO, :DATA_AVALIACAO, :OBS)")
                .setParameter("TIPO_SERVICO", avaliacaoDto.getTipoServico())
                .setParameter("ID_SERVICO", avaliacaoDto.getIdServico())
                .setParameter("ID_USU_EXECT", avaliacaoDto.getIdUsuExect())
                .setParameter("ID_USU_CONF", avaliacaoDto.getIdUsuConf())
                .setParameter("RESULTADO", avaliacaoDto.getResultado())
                .setParameter("DATA_AVALIACAO", avaliacaoDto.getDataAvaliacao())
                .setParameter("OBS", avaliacaoDto.getObs())
                .executeUpdate();
    }

    @Override
    public void reavaliar(AvaliacaoDto avaliacaoDto) {
        em.createNativeQuery("UPDATE AVALIACAO \n" +
                        "SET RESULT_REAVAL = :RESULT_REAVAL, DATA_REAVALIACAO = :DATA_REAVALIACAO, OBS = :OBS \n" +
                        "WHERE ID = :ID")
                .setParameter("id", avaliacaoDto.getIdAvaliacao())
                .setParameter("RESULT_REAVAL", avaliacaoDto.getResultReaval())
                .setParameter("DATA_REAVAL", avaliacaoDto.getDataReavaliacao())
                .setParameter("OBS", avaliacaoDto.getObs())
                .executeUpdate();
    }

    @Override
    public List<Avaliacao> listar() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM AVALIACAO \n" +
                "ODER BY ID");
        return query.getResultList();
    }

    @Override
    public Avaliacao buscarPorId(Integer idAvaliacao) {
        TypedQuery<Avaliacao> query = getEntityManager().createNamedQuery("Avaliacao.buscarPorId", Avaliacao.class)
                .setParameter("ID", idAvaliacao);
        return query.getSingleResult();
    }

    @Override
    public List<Avaliacao> buscarPorServico(Integer tipoServico) {
        TypedQuery<Avaliacao> query = getEntityManager().createNamedQuery("Avaliacao.buscarPorServico", Avaliacao.class)
                .setParameter("TIPO_SERVICO", tipoServico);
        return query.getResultList();
    }

    @Override
    public List<Avaliacao> buscarPorExecutor(Integer idUsuExect) {
        TypedQuery<Avaliacao> query = getEntityManager().createNamedQuery("Avaliacao.buscarPorExecutor", Avaliacao.class)
                .setParameter("ID_USU_EXECT", idUsuExect);
        return query.getResultList();
    }

    @Override
    public List<Avaliacao> buscarPorConferente(Integer idUsuConf) {
        TypedQuery<Avaliacao> query = getEntityManager().createNamedQuery("Avaliacao.buscarPorConferente", Avaliacao.class)
                .setParameter("ID_USU_CONF", idUsuConf);
        return query.getResultList();
    }
}
