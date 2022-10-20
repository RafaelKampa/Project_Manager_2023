package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.entity.TipoServico;

import java.util.List;

public interface TipoServicoRepository {
    void salvarNovoServico(TipoServicoDto tipoServicoDto);
    List<TipoServico> listar();
    TipoServico buscarPorid(Integer idTipoServico);
    void excluirServico(Integer idTipoServico);
    List<TipoServico> buscarPorNome(String nomeServico);
    void editarServico(TipoServicoDto tipoServicoDto);
}
