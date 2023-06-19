package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.RemuneracaoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RemuneracaoRepository {
    void salvarNovaRemuneracao (RemuneracaoDto remuneracaoDto);
    RemuneracaoDto buscarUltimaRemuneracaoUsuario(Integer idUsuario);
    Double buscarRemuneracaoPorMes(Integer idUsuario, Integer mesReferencia, Integer anoReferencia);
    List<RemuneracaoDto> listarRemuneracoesUsuario(Integer idUsuario);
}
