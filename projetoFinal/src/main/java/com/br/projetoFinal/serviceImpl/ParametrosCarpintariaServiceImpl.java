package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;
import com.br.projetoFinal.entity.ParametrosCarpintaria;
import com.br.projetoFinal.repository.ParametrosCarpintariaRepository;
import com.br.projetoFinal.service.ParametrosCarpintariaService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Service
public class ParametrosCarpintariaServiceImpl implements ParametrosCarpintariaService {

    @Autowired
    private ParametrosCarpintariaRepository parametrosCarpintariaRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarParametrosAvaliados(ParametrosCarpintariaDto parametrosCarpintariaDto) throws ExcecaoExemplo, SystemException {
        ParametrosCarpintaria parametrosCarpintaria = mapper.map(parametrosCarpintariaDto, ParametrosCarpintaria.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (parametrosCarpintariaDto.getIdAvaliacao() == 0) {
                throw new ExcecaoExemplo("ERR400", "É necessário informar a qual Avaliação essse Serviço é associado.");
            } else if (parametrosCarpintariaDto.getTipoCarpintaria().equals(null)) {
                throw new ExcecaoExemplo("ERR410", "É necessário informar se este serviço é de um pilar, uma viga ou uma laje.");
            } else if (parametrosCarpintariaDto.getDimensoes().equals(null)) {
                throw new ExcecaoExemplo("ERR411", "É necessário informar se as Dimensões são condizentes com o projeto");
            } else if (parametrosCarpintariaDto.getNivelOuPrumo().equals(null)) {
                throw new ExcecaoExemplo("ERR412", "É necessário informar se o Prumo ou Nível está correto para esse serviço");
            } else if (parametrosCarpintariaDto.getDimensoes().equals(null)) {
                throw new ExcecaoExemplo("ERR413", "É necessário informar se as Dimensões são condizentes com o projeto");
            } else if (parametrosCarpintariaDto.getEstanqueidade().equals(null)) {
                throw new ExcecaoExemplo("ERR414", "É necessário informar se a Estanqueidade está suficiente");
            } else {
                parametrosCarpintariaRepository.salvarParametrosAvaliados((ParametrosCarpintariaDto) parametrosCarpintaria);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ParametrosCarpintariaDto buscarPorId(Integer idParametrosCarpintaria) {
        return parametrosCarpintariaRepository.buscarPorId(idParametrosCarpintaria);
    }

    @Override
    public ParametrosCarpintariaDto buscarPorAvaliacao(Integer idAvaliacao) {
        return parametrosCarpintariaRepository.buscarPorAvaliacao(idAvaliacao);
    }

    @Override
    public void excluir(Integer idParametrosCarpintaria) throws SystemException {
        try {
            utx.begin();
            parametrosCarpintariaRepository.excluirPorId(idParametrosCarpintaria);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }

    }
}
