package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;
import com.br.projetoFinal.entity.ParametrosCarpintaria;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;

public interface ParametrosCarpintariaService {
    void salvarParametrosAvaliados(ParametrosCarpintariaDto parametrosCarpintariaDto) throws ExcecaoExemplo, SystemException;
    ParametrosCarpintariaDto buscarPorId(Integer idParametrosCarpintaria);
    ParametrosCarpintariaDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluir(Integer idParametrosCarpintaria) throws SystemException;
}
