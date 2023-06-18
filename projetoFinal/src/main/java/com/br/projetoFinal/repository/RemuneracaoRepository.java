package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.RemuneracaoDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface RemuneracaoRepository {
    void salvarNovaRemuneracao (RemuneracaoDto remuneracaoDto);
    Double buscarUltimaRemuneracaoUsuario(Integer idUsuario);
    Double buscarRemuneracaoPorMes(Integer idUsuario, Integer mesReferencia, Integer anoReferencia);
}
