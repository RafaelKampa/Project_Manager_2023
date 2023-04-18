package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosAlvenariaDto;
import com.br.projetoFinal.entity.ParametrosAlvenaria;

public interface ParametrosAlvenariaRepository {

    void salvarParametrosAvaliados (ParametrosAlvenariaDto parametrosAlvenariaDto);
    ParametrosAlvenariaDto buscarPorId(Integer idParametrosAlvenaria);
    ParametrosAlvenariaDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluirPorId(Integer idParametrosAlvenaria);
}
