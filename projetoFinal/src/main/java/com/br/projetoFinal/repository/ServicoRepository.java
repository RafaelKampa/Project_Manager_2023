package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.List;

public interface ServicoRepository {
    void salvarNovoServico(ServicoDto servicoDto) throws ExcecaoExemplo, SystemException;
    List<Servico> listar();
    List<Servico> listarAguardandoAvaliacao();
    List<Servico> listarAvaliados();
    Servico buscarPorId(Integer idServico);
    void excluirPorId(Integer idServico);
    List<Servico> buscarPorServico(String tipoServico);
}
