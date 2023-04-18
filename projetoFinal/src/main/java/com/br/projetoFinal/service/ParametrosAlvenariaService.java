package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.ParametrosAlvenariaDto;
import com.br.projetoFinal.entity.ParametrosAlvenaria;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;

public interface ParametrosAlvenariaService {
    void salvarParametrosAvaliados(ParametrosAlvenariaDto parametrosAlvenariaDto) throws ExcecaoExemplo, SystemException;
    ParametrosAlvenariaDto buscarPorId(Integer idParametrosAlvenaria);
    ParametrosAlvenariaDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluir(Integer idParametrosAlvenaria) throws SystemException;
}
