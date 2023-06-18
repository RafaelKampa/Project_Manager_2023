package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.RemuneracaoDto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;

public interface RemuneracaoService {
    void salvarNovaRemuneracao (RemuneracaoDto remuneracaoDto) throws ExcecaoExemplo, SystemException;
    Double buscarUltimaRemuneracaoUsuario (Integer idUsuario);
    Double buscarRemuneracaoPorMes(Integer idUsuario, Integer mesReferencia, Integer anoReferencia);
}
