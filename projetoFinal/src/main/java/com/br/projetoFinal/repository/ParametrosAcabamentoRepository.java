package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;
import com.br.projetoFinal.entity.ParametrosAcabamento;

public interface ParametrosAcabamentoRepository {

    void salvarParametrosAvaliados(ParametrosAcabamentoDto parametrosAcabamentoDto);
    ParametrosAcabamentoDto buscarPorId(Integer idParametrosAcabamento);
    ParametrosAcabamentoDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluirPorId(Integer idParametrosAcabamento);
}
