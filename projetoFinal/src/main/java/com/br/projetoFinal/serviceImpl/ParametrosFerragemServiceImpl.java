package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.ParametrosFerragemDto;
import com.br.projetoFinal.entity.ParametrosFerragem;
import com.br.projetoFinal.repository.ParametrosFerragemRepository;
import com.br.projetoFinal.service.ParametrosFerragemService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Service
public class ParametrosFerragemServiceImpl implements ParametrosFerragemService {

    @Autowired
    private ParametrosFerragemRepository parametrosFerragemRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarParametrosAvaliados(ParametrosFerragemDto parametrosFerragemDto) throws ExcecaoExemplo, SystemException {
        ParametrosFerragem parametrosFerragem = mapper.map(parametrosFerragemDto, ParametrosFerragem.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (parametrosFerragemDto.getIdAvaliacao() == 0) {
                throw new ExcecaoExemplo("ERR400", "É necessário informar a qual Avaliação essse Serviço é associado.");
            } else if (parametrosFerragemDto.getEspacamento().equals(null)) {
                throw new ExcecaoExemplo("ERR420", "É necessário confirmar se o espaçamento entre a ferragem e a forma é maior ou igual a 2,5cm.");
            } else if (parametrosFerragemDto.getQtdeAco().equals(null)) {
                throw new ExcecaoExemplo("ERR421", "É necessário informar o peso total do aço executado no serviço avaliado.");
            } else if (parametrosFerragemDto.getDistribuicao().equals(null)) {
                throw new ExcecaoExemplo("ERR422", "É necessário informar se as dimensões em projeto foram respeitadas");
            } else {
                parametrosFerragemRepository.salvarParametrosAvaliados((ParametrosFerragemDto) parametrosFerragem);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public ParametrosFerragem buscarPorId(Integer idParametrosFerragem) {
        return parametrosFerragemRepository.buscarPorId(idParametrosFerragem);
    }

    @Override
    public ParametrosFerragem buscarPorAvaliacao(Integer idAvaliacao) {
        return parametrosFerragemRepository.buscarPorAvaliacao(idAvaliacao);
    }

    @Override
    public void excluir(Integer idParametrosFerragem) {
        parametrosFerragemRepository.excluirPorId(idParametrosFerragem);
    }
}
