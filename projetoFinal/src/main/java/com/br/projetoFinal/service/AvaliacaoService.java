package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.entity.Avaliacao;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface AvaliacaoService {

    void avaliar(AvaliacaoDto avaliacaoDto) throws ExcecaoExemplo, SystemException;
    void reavaliar(AvaliacaoDto avaliacaoDto) throws ExcecaoExemplo, SystemException;
    List<AvaliacaoDto> listar();
    AvaliacaoDto buscarPorId(Integer idAvaliacao);
    List<AvaliacaoDto> buscarPorServico(Integer tipoServico);
    List<AvaliacaoDto> buscarPorExecutor(Integer idUsuExect);
    List<AvaliacaoDto> buscarPorConferente(Integer idUsuConf);

}
