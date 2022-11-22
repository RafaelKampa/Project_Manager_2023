package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface CentroDeCustoRepository {
    void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCusto) throws ExcecaoExemplo, SystemException;
    CentroDeCusto buscarPorId(Integer idCentroDeCusto);
    CentroDeCusto buscarPorNome(String nomeCentroDeCusto);
    void excluir(Integer idCentroDeCusto);
    List<CentroDeCusto> listarCentrosDeCusto();
}
