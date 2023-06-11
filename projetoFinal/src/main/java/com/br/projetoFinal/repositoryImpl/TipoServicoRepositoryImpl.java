package com.br.projetoFinal.repositoryImpl;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.repository.TipoServicoRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TipoServicoRepositoryImpl implements TipoServicoRepository {

    @Resource
    EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void salvarNovoServico(TipoServicoDto tipoServicoDto) {
        em.createNativeQuery("INSERT INTO TIPO_SERVICO_TABLE (NOME_SERVICO, UNIDADE_MEDIDA, VALOR_UNITARIO) \n" +
                        "VALUES (:NOME_SERVICO, :UNIDADE_MEDIDA, :VALOR_UNITARIO)")
                .setParameter("NOME_SERVICO", tipoServicoDto.getNomeServico())
                .setParameter("UNIDADE_MEDIDA", tipoServicoDto.getUnidadeMedida())
                .setParameter("VALOR_UNITARIO", tipoServicoDto.getValorUnitario())
                .executeUpdate();
    }

    @Override
    public List<TipoServicoDto> listar() {
        TypedQuery<TipoServicoDto> query = em.createNamedQuery("TipoServico.listar", TipoServicoDto.class);
        return query.getResultList();
    }

    @Override
    public TipoServicoDto buscarPorid(Integer idTipoServico) {
        TypedQuery<TipoServicoDto> query = em.createNamedQuery("TipoServico.buscarPorId", TipoServicoDto.class)
                .setParameter("ID_TIPO_SERVICO", idTipoServico);
        return query.getSingleResult();
    }

    @Override
    public List<TipoServicoDto> buscarPorNome(String nomeServico) {
        TypedQuery<TipoServicoDto> query = em.createNamedQuery("TipoServico.buscarPorNome", TipoServicoDto.class)
                .setParameter("NOME_SERVICO", nomeServico);
        return query.getResultList();
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void excluirServico(Integer idTipoServico) {
        TypedQuery<TipoServicoDto> query = em.createNamedQuery("TipoServico.excluirPorId", TipoServicoDto.class)
                .setParameter("ID_TIPO_SERVICO", idTipoServico);
    }
}
