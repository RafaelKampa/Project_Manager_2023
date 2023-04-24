package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.entity.TipoServico;

import java.util.List;

public interface TipoServicoRepository {
    void salvarNovoServico(TipoServicoDto tipoServicoDto);
    List<TipoServicoDto> listar();
    TipoServicoDto buscarPorid(Integer idTipoServico);
    List<TipoServicoDto> buscarPorNome(String nomeServico);
    void excluirServico(Integer idTipoServico);
}
