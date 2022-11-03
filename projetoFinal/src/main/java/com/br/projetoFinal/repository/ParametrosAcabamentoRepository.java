package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;
import com.br.projetoFinal.entity.ParametrosAcabamento;

public interface ParametrosAcabamentoRepository {

    void salvarParametrosAvaliados(ParametrosAcabamentoDto parametrosAcabamentoDto);
    ParametrosAcabamento buscarPorId(Integer idParametrosAcabamento);
    ParametrosAcabamento buscarPorAvaliacao(Integer idAvaliacao);
    void excluirPorId(Integer idParametrosAcabamento);
}
