package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosAlvenariaDto;
import com.br.projetoFinal.entity.ParametrosAlvenaria;

public interface ParametrosAlvenariaRepository {
    void salvarParametrosAvaliados (ParametrosAlvenariaDto parametrosAlvenariaDto);

    ParametrosAlvenaria buscarPorId(Integer idParametrosAlvenaria);

    ParametrosAlvenaria buscarPorAvaliacao(Integer idAvaliacao);

    void excluirPorId(Integer idParametrosAlvenaria);
}
