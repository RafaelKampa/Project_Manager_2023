package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.repository.ServicoRepository;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoServiceImpl {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico salvar(Servico servico) throws ExcecaoExemplo {
        if (servico.getTipoServico().equals(null)) {
            throw new ExcecaoExemplo("ERR002","É necessário informar o tipo de serviço.");
        } else if (servico.getDimensao().equals(null)) {
            throw new ExcecaoExemplo("ERR003", "É necessário informar a dimensão do serviço.");
        } else if (servico.getValor().equals(null)) {
            throw new ExcecaoExemplo("ERR004", "É necessário informar o valor do serviço");
        } else if (servico.getLocalExecucao().equals(null)) {
            throw new ExcecaoExemplo("ERR005", "É necessário informar o local do serviço");
        } else if (servico.getExecutor().equals(null)) { //Adicionar caso um funcionario não exista no banco de dados
            throw new ExcecaoExemplo("ESSE ERRO NÃO EXISTE", "É necessário informar funcionário responsável pela execução do serviço");
        } else if (servico.getDataInicio().equals(null)) { //Adicionar caso um funcionario não exista no banco de dados
            throw new ExcecaoExemplo("ERR007", "É necessário informar a data de início do serviço");
        } else if (servico.getConferente().equals(null)) { //Adicionar o nome de quem está cadastrando o serviço
            throw new ExcecaoExemplo("ESSE ERRO NÃO EXISTE", "É necessário informar funcionário responsável pela conferência do serviço");
        }
        return servicoRepository.save(servico);
    }

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Integer id) {
        return servicoRepository.findById(id).get();
    }

    public void excluir(Integer id) {
        servicoRepository.deleteById(id);
    }

}