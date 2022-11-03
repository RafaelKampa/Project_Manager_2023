package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;

public interface CentroDeCustoRepository {
    void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCusto);
    CentroDeCusto buscarPorId(Integer idCentroDeCusto);
    CentroDeCusto buscarPorNome(String nomeCentroDeCusto);
    void excluir(Integer idCentroDeCusto);
}
