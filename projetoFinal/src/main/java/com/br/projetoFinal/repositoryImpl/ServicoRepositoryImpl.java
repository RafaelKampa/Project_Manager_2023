package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.repository.ServicoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
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
        em.createNativeQuery("INSERT INTO SERVICO (TIPO_SERVICO, VALOR_UNITARIO, DIMENSAO, UNIDADE_MEDIDA, CENTRO_DE_CUSTO, LOCAL_EXECUCAO, EXECUTOR, CONFERENTE, DATA_INICIO, PREV_TERMINO, VALOR_TOTAL, OBS, IND_CONCLUIDO) \n" +
                        "VALUES (:TIPO_SERVICO, :VALOR_UNITARIO, :DIMENSAO, :UNIDADE_MEDIDA, :CENTRO_DE_CUSTO, :LOCAL_EXECUCAO, :EXECUTOR, :CONFERENTE, :DATA_INICIO, :PREV_TERMINO, :VALOR_TOTAL, :OBS, :IND_CONCLUIDO)")
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
                .setParameter("IND_CONCLUIDO", null)
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
    public List<ServicoDto> servicosAguardandoReaval() {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.servicosAguardandoReaval", ServicoDto.class);
        return query.getResultList();
    }

    @Override
    public ServicoDto buscarPorId(Integer idServico) {
        TypedQuery<ServicoDto> query = em.createNamedQuery("Servico.buscarPorId", ServicoDto.class)
                .setParameter("ID_SERVICO", idServico);
        return query.getSingleResult();
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

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void concluirServico(Integer idServico, Boolean indConcluido) {
        em.createNativeQuery("UPDATE SERVICO SET DATA_FINAL = :DATA_FINAL, IND_CONCLUIDO = :IND_CONCLUIDO WHERE ID_SERVICO = :ID_SERVICO")
                .setParameter("DATA_FINAL", new Date())
                .setParameter("IND_CONCLUIDO", indConcluido)
                .setParameter("ID_SERVICO", idServico)
                .executeUpdate();
    }
}
