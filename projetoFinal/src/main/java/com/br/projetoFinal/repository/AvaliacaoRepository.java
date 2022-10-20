package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.entity.Avaliacao;

import java.util.List;

public interface AvaliacaoRepository {
    void avaliar(AvaliacaoDto avaliacaoDto);
    void reavaliar(AvaliacaoDto avaliacaoDto);
    List<Avaliacao> listar();
    Avaliacao buscarPorId(Integer idAvaliacao);
    List<Avaliacao> buscarPorServico(Integer tipoServico);
    List<Avaliacao> buscarPorExecutor(Integer idUsuExect);
    List<Avaliacao> buscarPorConferente(Integer idUsuConf);
}
