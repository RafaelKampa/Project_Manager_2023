package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.ParametrosFerragemDto;
import com.br.projetoFinal.entity.ParametrosFerragem;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;

public interface ParametrosFerragemService {

    void salvarParametrosAvaliados(ParametrosFerragemDto parametrosFerragemDto) throws ExcecaoExemplo, SystemException;
    ParametrosFerragemDto buscarPorId(Integer idParametrosFerragem);
    ParametrosFerragemDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluir(Integer idParametrosFerragem) throws SystemException;
}
