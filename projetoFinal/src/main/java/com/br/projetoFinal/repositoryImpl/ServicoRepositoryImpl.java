package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.repository.ServicoRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServicoRepositoryImpl implements ServicoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvarNovoServico(ServicoDto servicoDto) {
        em.createNativeQuery("INSERT INTO servico (id, tipoServico, valorUnitario, dimensao, localExecucao, executor, conferente, dataInicio, " +
                        "valorTotal, situacao) " +
                        "VALUES (:id, :tipoServico, :valorUnitario, :dimensao, :localExecucao, :executor, :conferente, :dataInicio,:valorTotal, :situacao)")
                .setParameter("id", servicoDto.getId())
                .setParameter("tipoServico", servicoDto.getTipoServico())
                .setParameter("valorUnitario", servicoDto.getValorUnitario())
                .setParameter("dimensao", servicoDto.getDimensao())
                .setParameter("localExecucao", servicoDto.getLocalExecucao())
                .setParameter("executor", servicoDto.getExecutor())
                .setParameter("conferente", servicoDto.getConferente())
                .setParameter("dataInicio", servicoDto.getDataInicio())
                .setParameter("valorTotal", servicoDto.getDimensao()*servicoDto.getValorUnitario())
                .setParameter("situacao", servicoDto.getSituacao())
                .executeUpdate();
    }

    @Override
    public List<Servico> listar() {
        Query query = getEntityManager().createNativeQuery("SELECT * FROM servico");
        return query.getResultList();
    }

    @Override
    public Servico buscarPorId(Integer id) {
        TypedQuery<Servico> query = (TypedQuery<Servico>) getEntityManager().createNativeQuery("SELECT * FROM servico WHERE id = :id")
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void excluirPorId(Integer id) {
        em.createNativeQuery("DELETE * FROM servico WHERE id = :id")
                .setParameter("id", id);
    }

    @Override
    public List<Servico> buscarPorServico(String tipoServico) {
        TypedQuery<Servico> query = (TypedQuery<Servico>) getEntityManager().createNativeQuery("SELECT * FROM servico WHERE tipoServico = :tipoServico")
                .setParameter("tipoServico", tipoServico);
        return query.getResultList();
    }
}
