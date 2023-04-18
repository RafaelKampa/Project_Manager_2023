package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface CentroDeCustoRepository {
    void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCusto) throws ExcecaoExemplo, SystemException;
    CentroDeCustoDto buscarCentroPorId(Integer idCentroDeCusto);
    CentroDeCustoDto buscarPorNome(String nomeCentroDeCusto);
    void excluir(Integer idCentroDeCusto);
    List<CentroDeCustoDto> listarCentrosDeCusto();
}
