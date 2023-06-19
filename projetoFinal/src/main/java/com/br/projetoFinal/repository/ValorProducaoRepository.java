package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ValorProducaoDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ValorProducaoRepository {
    void inserirValorProducao (ValorProducaoDto valorProducaoDto);
    Double buscarValorMensal (Integer idUsuario, Integer mesReferencia, Integer anoReferencia);
    List<ValorProducaoDto> listarProducaoPorUsuario(Integer idUsuario);
}
