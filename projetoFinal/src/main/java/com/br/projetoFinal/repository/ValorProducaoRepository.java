package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ValorProducaoDto;

public interface ValorProducaoRepository {
    void inserirValorProducao (ValorProducaoDto valorProducaoDto);
    Double buscarValorMensal (Integer idUsuario, Integer mesReferencia, Integer anoReferencia);
}
