package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.ParametrosAlvenariaDto;
import com.br.projetoFinal.entity.ParametrosAlvenaria;
import com.br.projetoFinal.repository.ParametrosAlvenariaRepository;
import com.br.projetoFinal.service.ParametrosAlvenariaService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Service
public class ParametrosAlvenariaServiceImpl implements ParametrosAlvenariaService {

    @Autowired
    private ParametrosAlvenariaRepository parametrosAlvenariaRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarParametrosAvaliados(ParametrosAlvenariaDto parametrosAlvenariaDto) throws ExcecaoExemplo, SystemException {
        ParametrosAlvenaria parametrosAlvenaria = mapper.map(parametrosAlvenariaDto, ParametrosAlvenaria.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (parametrosAlvenariaDto.getIdAvaliacao() == 0) {
                throw new ExcecaoExemplo("ERR401", "É necessário informar a avaliação a qual estes dados serão associados.");
            } else if (parametrosAlvenariaDto.getPrumo().equals(null)) {
                throw new ExcecaoExemplo("ERR402", "É necessário informar se o Prumo está Correto ou Incorreto.");
            } else if (parametrosAlvenariaDto.getNivel().equals(null)) {
                throw new ExcecaoExemplo("ERR403", "É necessário informar se o Nível está Correto ou Incorreto");
            } else if (parametrosAlvenariaDto.getAlinhamento().equals(null)) {
                throw new ExcecaoExemplo("ERR404", "É necessário informar se o Alinhamento está Correto ou Incorreto");
            } else if (parametrosAlvenariaDto.getDimensoes().equals(null)) {
                throw new ExcecaoExemplo("ERR405", "É necessário informar se as Dimensões são condizentes com o projeto");
            } else if (parametrosAlvenariaDto.getIntegridade().equals(null)) {
                throw new ExcecaoExemplo("ERR406", "É necessário informar se a Integridade da alvenaria está Correta ou Incorreta");
            } else if (parametrosAlvenariaDto.getLimpeza().equals(null)) {
                throw new ExcecaoExemplo("ERR406", "É necessário informar se a Limpeza do serviço está Correta ou Incorreta");
            } else {
                parametrosAlvenariaRepository.salvarParametrosAvaliados((ParametrosAlvenariaDto) parametrosAlvenaria);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ParametrosAlvenaria buscarPorId(Integer idParametrosAlvenaria) {
        return parametrosAlvenariaRepository.buscarPorId(idParametrosAlvenaria);
    }

    @Override
    public ParametrosAlvenaria buscarPorAvaliacao(Integer idAvaliacao) {
        return parametrosAlvenariaRepository.buscarPorId(idAvaliacao);
    }

    @Override
    public void excluir(Integer idParametrosAlvenaria){
        parametrosAlvenariaRepository.excluirPorId(idParametrosAlvenaria);
    }


}
