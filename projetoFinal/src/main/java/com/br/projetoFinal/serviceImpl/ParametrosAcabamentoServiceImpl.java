package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;
import com.br.projetoFinal.entity.ParametrosAcabamento;
import com.br.projetoFinal.repository.ParametrosAcabamentoRepository;
import com.br.projetoFinal.service.ParametrosAcabamentoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Service
public class ParametrosAcabamentoServiceImpl implements ParametrosAcabamentoService {

    @Autowired
    private ParametrosAcabamentoRepository parametrosAcabamentoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarParametrosAvaliados(ParametrosAcabamentoDto parametrosAcabamentoDto) throws ExcecaoExemplo, SystemException {
        ParametrosAcabamento parametrosAcabamento = mapper.map(parametrosAcabamentoDto, ParametrosAcabamento.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (parametrosAcabamentoDto.getIdAvaliacao() == 0) {
                throw new ExcecaoExemplo("ERR400", "É necessário informar a qual Avaliação essse Serviço é associado.");
            } else if (parametrosAcabamentoDto.getDimensoes().equals(null)) {
                throw new ExcecaoExemplo("ERR431", "É necessário informar se o Prumo está Correto ou Incorreto.");
            } else if (parametrosAcabamentoDto.getReguamento().equals(null)) {
                throw new ExcecaoExemplo("ERR432", "É necessário informar se o Nível está Correto ou Incorreto");
            } else if (parametrosAcabamentoDto.getAlisamento().equals(null)) {
                throw new ExcecaoExemplo("ERR433", "É necessário informar se o Alinhamento está Correto ou Incorreto");
            } else {
                parametrosAcabamentoRepository.salvarParametrosAvaliados((ParametrosAcabamentoDto) parametrosAcabamento);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ParametrosAcabamento buscarPorId(Integer idParametrosAcabamento) {
        return parametrosAcabamentoRepository.buscarPorId(idParametrosAcabamento);
    }

    @Override
    public ParametrosAcabamento buscarPorAvaliacao(Integer idAvaliacao) {
        return parametrosAcabamentoRepository.buscarPorAvaliacao(idAvaliacao);
    }

    @Override
    public void excluir(Integer idParametrosAcabamento){
        parametrosAcabamentoRepository.excluirPorId(idParametrosAcabamento);
    }
}
