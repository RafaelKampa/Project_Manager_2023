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
        em.createNativeQuery("INSERT INTO SERVICO (TIPO_SERVICO, VALOR_UNITARIO, DIMENSAO, UNIDADE_MEDIDA, CENTRO_DE_CUSTO, LOCAL_EXECUCAO, EXECUTOR, CONFERENTE, DATA_INICIO, PREV_TERMINO, VALOR_TOTAL, OBS) \n" +
                        "VALUES (:TIPO_SERVICO, :VALOR_UNITARIO, :DIMENSAO, :UNIDADE_MEDIDA, :CENTRO_DE_CUSTO, :LOCAL_EXECUCAO, :EXECUTOR, :CONFERENTE, :DATA_INICIO, :PREV_TERMINO, :VALOR_TOTAL, :OBS)")
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
    public List<ServicoDto> listar() {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.listarTodosServicos", ServicoDto.class);
        return query.getResultList();
    }

    @Override
    public List<ServicoDto> listarAguardandoAvaliacao() {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.servicosAguardandoAval", ServicoDto.class);
        return query.getResultList();
    }

    @Override
    public List<ServicoDto> listarAvaliados() {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.servicosAvaliados", ServicoDto.class);
        return query.getResultList();
    }

    @Override
    public  List<ServicoDto> buscarPorId(Integer idServico) {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.buscarPorId", ServicoDto.class)
                .setParameter("ID_SERVICO", idServico);
        return query.getResultList();
    }

    @Override
    public List<ServicoDto> buscarPorServico(String tipoServico) {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.buscarPorTipo", ServicoDto.class)
                .setParameter("TIPO_SERVICO", tipoServico);
        return query.getResultList();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void excluirPorId(Integer idServico) {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.excluirPorId", ServicoDto.class)
                .setParameter("ID_SERVICO", idServico);
    }
}
