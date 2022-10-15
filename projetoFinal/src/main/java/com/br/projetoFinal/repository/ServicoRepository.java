package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Servico;

import java.util.List;

public interface ServicoRepository {
    void salvarNovoServico(ServicoDto servicoDto);
    List<Servico> listar();
    Servico buscarPorId(Integer id);
    void excluirPorId(Integer id);
    List<Servico> buscarPorServico(String tipoServico);
}
