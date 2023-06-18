package com.br.projetoFinal.service;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.dto.ValorTotalCentroPeriodoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import javax.transaction.SystemException;
import java.util.Date;
import java.util.List;

public interface ServicoService {
    void salvarNovoServico(ServicoDto servicoDto) throws ExcecaoExemplo, SystemException;
    List<ServicoDto> listar();
    List<ServicoDto> listarAguardandoAvaliacao();
    List<ServicoDto> listarAvaliados();
    List<ServicoDto> servicosAguardandoReaval();
    ServicoDto buscarPorId(Integer idServico);
    List<ServicoDto> buscarPorServico(String tipoServico);
    void excluir(Integer idServico) throws SystemException;
    void concluirServico(Integer idServico, Boolean indConcluido, String conferente) throws ExcecaoExemplo, SystemException;
    List<ValorTotalCentroPeriodoDto> buscarValorTotalPorCentro(String centroDeCusto, Integer mesReferencia, Integer anoReferencia);
}
