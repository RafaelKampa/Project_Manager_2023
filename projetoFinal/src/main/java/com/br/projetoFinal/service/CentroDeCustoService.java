package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;

public interface CentroDeCustoService {

    void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCustoDto) throws ExcecaoExemplo, SystemException;
    CentroDeCusto buscarPorId(Integer idCentroDeCusto);
    CentroDeCusto buscarPorNome(String nomeCentroDeCusto);
    void excluir(Integer idCentroDeCusto);
}
