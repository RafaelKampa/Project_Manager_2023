package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.entity.TipoServico;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface TipoServicoService {
    void salvarNovoServico(TipoServicoDto tipoServicoDto) throws ExcecaoExemplo, SystemException;
    List<TipoServicoDto> listar();
    TipoServicoDto buscarPorId(Integer idTipoServico);
    List<TipoServicoDto> buscarPorNome(String nomeServico);
    void excluir(Integer idTipoServico) throws SystemException;
}
