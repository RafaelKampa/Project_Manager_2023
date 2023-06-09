package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.RemuneracaoDto;
import com.br.projetoFinal.dto.ValorProducaoDto;
import com.br.projetoFinal.entity.ValorProducao;
import com.br.projetoFinal.repository.ValorProducaoRepository;
import com.br.projetoFinal.service.ValorProducaoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Service
public class ValorProducaoServiceImpl implements ValorProducaoService {

    @Autowired
    private ValorProducaoRepository valorProducaoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void inserirValorProducao(ValorProducaoDto valorProducaoDto) throws ExcecaoExemplo, SystemException {
        ValorProducao valorProducao = mapper.map(valorProducaoDto, ValorProducao.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (valorProducaoDto.getValorServico().equals(null)) {
                throw new ExcecaoExemplo("ERR601", "É necessário informar o valor do serviço.");
            } else if (valorProducaoDto.getIdUsuario().equals(null)) {
                throw new ExcecaoExemplo("ERR602", "É necessário informar o id de usuário");
            } else if (valorProducaoDto.getMesReferencia().equals(null)) {
                throw new ExcecaoExemplo("ERR603", "É necessário informar o mês de referência do início da remuneração");
            } else if (valorProducaoDto.getAnoReferencia().equals(null)) {
                throw new ExcecaoExemplo("ERR604", "É necessário informar o ano de referência do início da remuneração");
            } else if (valorProducaoDto.getIdCentroDeCusto().equals(null)) {
                throw new ExcecaoExemplo("ERR605", "É necessário informar o centro de custo.");
            } else if (valorProducaoDto.getIdServico().equals(null)) {
                throw new ExcecaoExemplo("ERR606", "É necessário informar o serviço.");
            } else {
                valorProducaoRepository.inserirValorProducao((ValorProducaoDto) valorProducao);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Double buscarValorMensal(Integer idUsuario, Integer mesReferencia, Integer anoReferencia) {
        return valorProducaoRepository.buscarValorMensal(idUsuario, mesReferencia, anoReferencia);
    }
}
