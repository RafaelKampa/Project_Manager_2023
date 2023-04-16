package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;
import com.br.projetoFinal.entity.ParametrosAcabamento;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;

public interface ParametrosAcabamentoService {

    void salvarParametrosAvaliados(ParametrosAcabamentoDto parametrosAcabamentoDto) throws ExcecaoExemplo, SystemException;
    ParametrosAcabamentoDto buscarPorId(Integer idParametrosAcabamento);
    ParametrosAcabamentoDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluir(Integer idParametrosAcabamento) throws SystemException;
}
