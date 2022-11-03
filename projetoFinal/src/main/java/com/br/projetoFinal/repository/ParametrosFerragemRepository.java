package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosFerragemDto;
import com.br.projetoFinal.entity.ParametrosFerragem;

public interface ParametrosFerragemRepository {
    void salvarParametrosAvaliados(ParametrosFerragemDto parametrosFerragemDto);

    ParametrosFerragem buscarPorId(Integer idParametrosFerragem);

    ParametrosFerragem buscarPorAvaliacao(Integer idAvaliacao);

    void excluirPorId(Integer idParametrosFerragem);
}
