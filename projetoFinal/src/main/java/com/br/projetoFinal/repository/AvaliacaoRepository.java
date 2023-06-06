package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.entity.Avaliacao;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.Date;
import java.util.List;

public interface AvaliacaoRepository {
    void avaliar(AvaliacaoDto avaliacaoDto);
    void reavaliar(AvaliacaoDto avaliacaoDto);
    List<AvaliacaoDto> listar();
    AvaliacaoDto buscarPorId(Integer idAvaliacao);
    List<AvaliacaoDto> buscarPorServico(Integer tipoServico);
    List<AvaliacaoDto> buscarPorExecutor(Integer idUsuExect);
    List<AvaliacaoDto> buscarPorConferente(Integer idUsuConf);
    Integer buscarUltimoId();
}
