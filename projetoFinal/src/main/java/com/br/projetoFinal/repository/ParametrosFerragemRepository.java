package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ParametrosFerragemDto;
import com.br.projetoFinal.entity.ParametrosFerragem;

public interface ParametrosFerragemRepository {
    void salvarParametrosAvaliados(ParametrosFerragemDto parametrosFerragemDto);
    ParametrosFerragemDto buscarPorId(Integer idParametrosFerragem);
    ParametrosFerragemDto buscarPorAvaliacao(Integer idAvaliacao);
    void excluirPorId(Integer idParametrosFerragem);
}
