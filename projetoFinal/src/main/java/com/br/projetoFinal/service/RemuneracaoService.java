package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.RemuneracaoDto;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface RemuneracaoService {
    void salvarNovaRemuneracao (RemuneracaoDto remuneracaoDto) throws ExcecaoExemplo, SystemException;
    RemuneracaoDto buscarUltimaRemuneracaoUsuario (Integer idUsuario);
    Double buscarRemuneracaoPorMes(Integer idUsuario, Integer mesReferencia, Integer anoReferencia);
    List<RemuneracaoDto> listarRemuneracoesUsuario(Integer idUsuario);
}
