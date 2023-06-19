package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.ValorProducaoDto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface ValorProducaoService {
    void inserirValorProducao (ValorProducaoDto valorProducaoDto) throws ExcecaoExemplo, SystemException;
    Double buscarValorMensal (Integer idUsuario, Integer mesReferencia, Integer anoReferencia);
    List<ValorProducaoDto> listarProducaoPorUsuario(Integer idUsuario);
}
