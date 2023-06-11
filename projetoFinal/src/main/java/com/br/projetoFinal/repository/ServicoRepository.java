package com.br.projetoFinal.repository;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;

import javax.transaction.SystemException;
import java.util.Date;
import java.util.List;

public interface ServicoRepository {
    void salvarNovoServico(ServicoDto servicoDto) throws ExcecaoExemplo, SystemException;
    List<ServicoDto> listar();
    List<ServicoDto> listarAguardandoAvaliacao();
    List<ServicoDto> listarAvaliados();
    List<ServicoDto> servicosAguardandoReaval();
    ServicoDto buscarPorId(Integer idServico);
    void excluirPorId(Integer idServico);
    List<ServicoDto> buscarPorServico(String tipoServico);
    void concluirServico(Integer idServico, Boolean indConcluido);
}
