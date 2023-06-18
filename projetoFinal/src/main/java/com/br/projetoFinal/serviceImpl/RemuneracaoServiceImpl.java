package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.RemuneracaoDto;
import com.br.projetoFinal.entity.Remuneracao;
import com.br.projetoFinal.repository.RemuneracaoRepository;
import com.br.projetoFinal.service.RemuneracaoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@Service
public class RemuneracaoServiceImpl implements RemuneracaoService {

    @Autowired
    private RemuneracaoRepository remuneracaoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarNovaRemuneracao(RemuneracaoDto remuneracaoDto) throws ExcecaoExemplo, SystemException {
        Remuneracao remuneracao = mapper.map(remuneracaoDto, Remuneracao.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (remuneracaoDto.getFuncao().equals(null)) {
                throw new ExcecaoExemplo("ERR501", "É necessário informar qual a função do usuário.");
            } else if (remuneracaoDto.getIdUsuario().equals(null)) {
                throw new ExcecaoExemplo("ERR502", "É necessário informar o id de usuário");
            } else if (remuneracaoDto.getMesReferencia().equals(null)) {
                throw new ExcecaoExemplo("ERR503", "É necessário informar o mês de referência do início da remuneração");
            } else if (remuneracaoDto.getAnoReferencia().equals(null)) {
                throw new ExcecaoExemplo("ERR504", "É necessário informar o ano de referência do início da remuneração");
            } else if (remuneracaoDto.getValor().equals(null)) {
                throw new ExcecaoExemplo("ERR505", "É necessário informar o valor da remuneração");
            } else {
                remuneracaoRepository.salvarNovaRemuneracao((RemuneracaoDto) remuneracao);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Double buscarUltimaRemuneracaoUsuario(Integer idUsuario) {
        return remuneracaoRepository.buscarUltimaRemuneracaoUsuario(idUsuario);
    }

    @Override
    public Double buscarRemuneracaoPorMes(Integer idUsuario, Integer mesReferencia, Integer anoReferencia) {
        return remuneracaoRepository.buscarRemuneracaoPorMes(idUsuario, mesReferencia, anoReferencia);
    }
}
