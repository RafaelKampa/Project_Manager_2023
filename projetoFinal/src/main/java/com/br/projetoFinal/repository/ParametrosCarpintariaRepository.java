package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;
import com.br.projetoFinal.entity.ParametrosCarpintaria;

public interface ParametrosCarpintariaRepository {
    void salvarParametrosAvaliados(ParametrosCarpintariaDto parametrosCarpintariaDto);
    ParametrosCarpintaria buscarPorId(Integer idParametrosCarpintaria);
    ParametrosCarpintaria buscarPorAvaliacao(Integer idAvaliacao);
    void excluirPorId(Integer idParametrosCarpintaria);

}
