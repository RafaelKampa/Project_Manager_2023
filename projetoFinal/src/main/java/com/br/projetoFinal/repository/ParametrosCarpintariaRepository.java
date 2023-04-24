package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;
import com.br.projetoFinal.entity.ParametrosCarpintaria;

public interface ParametrosCarpintariaRepository {
    void salvarParametrosAvaliados(ParametrosCarpintariaDto parametrosCarpintariaDto);
    ParametrosCarpintariaDto buscarPorId(Integer idParametrosCarpintaria);
    ParametrosCarpintariaDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluirPorId(Integer idParametrosCarpintaria);

}
