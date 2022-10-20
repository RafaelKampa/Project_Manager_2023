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
        em.createNativeQuery("INSERT INTO SERVICO (ID, TIPO_SERVICO, VALOR_UNITARIO, DIMENSAO, LOCAL_EXECUCAO, EXECUTOR, CONFERENTE, DATA_INICIO, VALOR_TOTAL, SITUACAO) \r\n" +
                        "VALUES (:ID, :TIPO_SERVICO, :VALOR_UNITARIO, :DIMENSAO, :LOCAL_EXECUCAO, :EXECUTOR, :CONFERENTE, :DATA_INICIO, :VALOR_TOTAL, :SITUACAO)")
                .setParameter("ID", servicoDto.getIdServico())
                .setParameter("TIPO_SERVICO", servicoDto.getTipoServico())
                .setParameter("VALOR_UNITARIO", servicoDto.getValorUnitario())
                .setParameter("DIMENSAO", servicoDto.getDimensao())
                .setParameter("LOCAL_EXECUCAO", servicoDto.getLocalExecucao())
                .setParameter("EXECUTOR", servicoDto.getExecutor())
                .setParameter("CONFERENTE", servicoDto.getConferente())
                .setParameter("DATA_INICIO", servicoDto.getDataInicio())
                .setParameter("VALOR_TOTAL", servicoDto.getDimensao()*servicoDto.getValorUnitario())
                .setParameter("SITUACAO", servicoDto.getSituacao())
                .executeUpdate();
    }

    @Override
    public List<Servico> listar() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM servico");
        return query.getResultList();
    }

    @Override
    public Servico buscarPorId(Integer idServico) {
        TypedQuery<Servico> query = (TypedQuery<Servico>) getEntityManager().createNativeQuery("SELECT * FROM SERVICO WHERE ID = :ID")
                .setParameter("ID", idServico);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer idServico) {
        em.createNativeQuery("DELETE * FROM SERVICO WHERE ID = :ID")
                .setParameter("ID", idServico);
    }

    @Override
    public List<Servico> buscarPorServico(String tipoServico) {
        TypedQuery<Servico> query = (TypedQuery<Servico>) getEntityManager().createNativeQuery("SELECT * FROM SERVICO WHERE TIPO_SERVICO = :TIPO_SERVICO")
                .setParameter("TIPO_SERVICO", tipoServico);
        return query.getResultList();
    }
}
