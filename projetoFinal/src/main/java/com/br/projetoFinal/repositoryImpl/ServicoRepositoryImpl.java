package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.repository.ServicoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ServicoRepositoryImpl implements ServicoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void salvarNovoServico(ServicoDto servicoDto) {
        em.createNativeQuery("INSERT INTO SERVICO (ID_SERVICO, TIPO_SERVICO, VALOR_UNITARIO, DIMENSAO, UNIDADE_MEDIDA, CENTRO_DE_CUSTO, LOCAL_EXECUCAO, EXECUTOR, CONFERENTE, DATA_INICIO, PREV_TERMINO, VALOR_TOTAL, OBS) \n" +
                        "VALUES (SELECT MAX(ID_SERVICO) FROM SERVICO + 1, :TIPO_SERVICO, :VALOR_UNITARIO, :DIMENSAO, :UNIDADE_MEDIDA, :CENTRO_DE_CUSTO, :LOCAL_EXECUCAO, :EXECUTOR, :CONFERENTE, :DATA_INICIO, :PREV_TERMINO, :VALOR_TOTAL, :OBS)")
                .setParameter("TIPO_SERVICO", servicoDto.getTipoServico())
                .setParameter("VALOR_UNITARIO", servicoDto.getValorUnitario())
                .setParameter("DIMENSAO", servicoDto.getDimensao())
                .setParameter("UNIDADE_MEDIDA", servicoDto.getUnidadeMedida())
                .setParameter("CENTRO_DE_CUSTO", servicoDto.getCentroDeCusto())
                .setParameter("LOCAL_EXECUCAO", servicoDto.getLocalExecucao())
                .setParameter("EXECUTOR", servicoDto.getExecutor())
                .setParameter("CONFERENTE", servicoDto.getConferente())
                .setParameter("DATA_INICIO", servicoDto.getDataInicio())
                .setParameter("PREV_TERMINO", servicoDto.getPrevisaoTermino())
                .setParameter("VALOR_TOTAL", servicoDto.getValorUnitario() * servicoDto.getDimensao())
                .setParameter("OBS", servicoDto.getObs())
                .executeUpdate();
    }

    @Override
    public List<Servico> listar() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM SERVICO \n" +
                "ORDER BY ID_SERVICO");
        return query.getResultList();
    }

    @Override
    public List<Servico> listarAguardandoAvaliacao() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM SERVICO \n" +
                "WHERE DATA_FINAL IS NULL \n" +
                "ORDER BY ID_SERVICO");
        return query.getResultList();
    }

    @Override
    public List<Servico> listarAvaliados() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM SERVICO \n" +
                "WHERE DATA_FINAL IS NOT NULL \n" +
                "ORDER BY ID_SERVICO");
        return query.getResultList();
    }

    @Override
    public  List<Servico> buscarPorId(Integer idServico, String tipoServico) {
        Query query = (Query) getEntityManager().createNativeQuery("SELECT * FROM SERVICO \n" +
                "WHERE ID_SERVICO = :ID_SERVICO AND TIPO_SERVICO = :TIPO_SERVICO")
                .setParameter("ID_SERVICO", idServico)
                .setParameter("TIPO_SERVICO", tipoServico);
        return query.getResultList();
    }

    @Override
    public void excluirPorId(Integer idServico) {
        em.createNativeQuery("DELETE FROM SERVICO \n" +
                        "WHERE ID_SERVICO = :ID_SERVICO")
                .setParameter("ID_SERVICO", idServico);
    }

    @Override
    public List<Servico> buscarPorServico(String tipoServico) {
        TypedQuery<Servico> query = getEntityManager().createNamedQuery("Servico.buscarPorServico", Servico.class)
                .setParameter("TIPO_SERVICO", tipoServico);
        return query.getResultList();
    }
}
