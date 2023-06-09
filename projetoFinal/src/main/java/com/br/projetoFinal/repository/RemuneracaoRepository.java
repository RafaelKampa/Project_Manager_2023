package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.RemuneracaoDto;

public interface RemuneracaoRepository {
    void salvarNovaRemuneracao (RemuneracaoDto remuneracaoDto);
    Double buscarUltimaRemuneracaoUsuario(Integer idUsuario);
}
