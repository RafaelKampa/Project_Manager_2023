package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.repository.ServicoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ServicoRepositoryImpl implements ServicoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarNovoServico(ServicoDto servicoDto) {
        em.createNativeQuery("INSERT INTO SERVICO (TIPO_SERVICO, VALOR_UNITARIO, DIMENSAO, LOCAL_EXECUCAO, EXECUTOR, CONFERENTE, DATA_INICIO, PREV_TERMINO, VALOR_TOTAL, OBS) \n" +
                        "VALUES (:TIPO_SERVICO, :VALOR_UNITARIO, :DIMENSAO, :LOCAL_EXECUCAO, :EXECUTOR, :CONFERENTE, :DATA_INICIO, :PREV_TERMINO, :VALOR_TOTAL, :OBS)")
                .setParameter("TIPO_SERVICO", servicoDto.getTipoServico())
                .setParameter("VALOR_UNITARIO", servicoDto.getValorUnitario())
                .setParameter("DIMENSAO", servicoDto.getDimensao())
                .setParameter("LOCAL_EXECUCAO", servicoDto.getLocalExecucao())
                .setParameter("EXECUTOR", servicoDto.getExecutor())
                .setParameter("CONFERENTE", servicoDto.getConferente())
                .setParameter("DATA_INICIO", servicoDto.getDataInicio())
                .setParameter("PREV_TERMINO", servicoDto.getPrevisaoTermino())
                .setParameter("VALOR_TOTAL", servicoDto.getDimensao()*servicoDto.getValorUnitario())
                .setParameter("OBS", servicoDto.getObs())
                .executeUpdate();
    }

    @Override
    public List<Servico> listar() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM SERVICO \n" +
                "ORDER BY ID");
        return query.getResultList();
    }

    @Override
    public Servico buscarPorId(Integer idServico) {
        TypedQuery<Servico> query = getEntityManager().createNamedQuery("Servico.buscarPorId", Servico.class)
                .setParameter("ID", idServico);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer idServico) {
        em.createNativeQuery("DELETE * FROM SERVICO \n" +
                        "WHERE ID = :ID")
                .setParameter("ID", idServico);
    }

    @Override
    public List<Servico> buscarPorServico(String tipoServico) {
        TypedQuery<Servico> query = getEntityManager().createNamedQuery("Servico.buscarPorServico", Servico.class)
                .setParameter("TIPO_SERVICO", tipoServico);
        return query.getResultList();
    }
}
