package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.repository.ServicoRepository;
import com.br.projetoFinal.service.ServicoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService{

    @Autowired
    private ServicoRepository servicoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarNovoServico(ServicoDto servicoDto) throws ExcecaoExemplo, SystemException {
        Servico servico = mapper.map(servicoDto, Servico.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (servicoDto.getTipoServico().equals(null)) {
                throw new ExcecaoExemplo("ERR100", "É necessário informar o tipo de serviço.");
            } else if (servicoDto.getDimensao().equals(null)) {
                throw new ExcecaoExemplo("ERR101", "É necessário informar a dimensão do serviço.");
            } else if (servicoDto.getValorUnitario().equals(null)) {
                throw new ExcecaoExemplo("ERR102", "É necessário informar o valor do serviço");
            } else if (servicoDto.getLocalExecucao().equals(null)) {
                throw new ExcecaoExemplo("ERR103", "É necessário informar o centro de custo do serviço");
            } else if (servicoDto.getCentroDeCusto().equals(null)) {
                throw new ExcecaoExemplo("ERR104", "É necessário informar o local do serviço");
            } else if (servicoDto.getExecutor().equals(null)) {
                throw new ExcecaoExemplo("ERR105", "É necessário informar funcionário responsável pela execução do serviço");
            } else if (servicoDto.getConferente().equals(null)) {
                throw new ExcecaoExemplo("ERR106", "É necessário informar funcionário responsável pela conferência do serviço");
            } else if (servicoDto.getDataInicio().equals(null)) {
                throw new ExcecaoExemplo("ERR107", "É necessário informar a data de início do serviço");
            } else {
                servicoRepository.salvarNovoServico((ServicoDto) servico);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<ServicoDto> listar() {
        return servicoRepository.listar();
    }

    @Override
    public List<ServicoDto> listarAguardandoAvaliacao() {
        return servicoRepository.listarAguardandoAvaliacao();
    }

    @Override
    public List<ServicoDto> listarAvaliados() {
        return servicoRepository.listarAvaliados();
    }

    @Override
    public List<ServicoDto> buscarPorId(Integer idServico) {
        return servicoRepository.buscarPorId(idServico);
    }

    @Override
    public List<ServicoDto> buscarPorServico(String tipoServico){
        return servicoRepository.buscarPorServico(tipoServico);
    }

    @Override
    public void excluir(Integer idServico){
        servicoRepository.excluirPorId(idServico);
    }


}