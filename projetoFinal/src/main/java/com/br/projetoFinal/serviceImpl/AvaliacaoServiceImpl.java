package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Avaliacao;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.repository.AvaliacaoRepository;
import com.br.projetoFinal.service.AvaliacaoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void avaliar(AvaliacaoDto avaliacaoDto) throws ExcecaoExemplo, SystemException {
        Avaliacao avaliacao = mapper.map(avaliacaoDto, Avaliacao.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (avaliacaoDto.getTipoServico() == 0) {
                throw new ExcecaoExemplo("ERR201", "É necessário informar o tipo de serviço.");
            } else if (avaliacaoDto.getIdServico() == 0) {
                throw new ExcecaoExemplo("ERR202", "É necessário informar o serviço a ser avaliado.");
            } else if (avaliacaoDto.getIdUsuExect() == 0) {
                throw new ExcecaoExemplo("ERR203", "É necessário informar o funcionário a ser avaliado");
            } else if (avaliacaoDto.getIdUsuConf() == 0) {
                throw new ExcecaoExemplo("ERR204", "É necessário informar o avaliador do serviço");
            } else if (avaliacaoDto.getDataAvaliacao().equals(null)) {
                throw new ExcecaoExemplo("ERR205", "É necessário informar a data da avaliação");
            } else if (avaliacaoDto.getResultado().equals(null)) {
                throw new ExcecaoExemplo("ERR206", "É necessário informar o resultado da avaliação");
            } else {
                avaliacaoRepository.avaliar((AvaliacaoDto) avaliacao);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void reavaliar(AvaliacaoDto avaliacaoDto) throws ExcecaoExemplo, SystemException {
        Avaliacao avaliacao = mapper.map(avaliacaoDto, Avaliacao.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (avaliacaoDto.getIdServico() == 0) {
                throw new ExcecaoExemplo("ERR207", "É necessário informar o serviço a ser avaliado.");
            } else if (avaliacaoDto.getDataReavaliacao().equals(null)) {
                throw new ExcecaoExemplo("ERR208", "É necessário informar a data da reavaliação");
            } else if (avaliacaoDto.getResultReaval().equals(null)) {
                throw new ExcecaoExemplo("ERR209", "É necessário informar o resultado da reavaliação");
            } else {
                avaliacaoRepository.reavaliar((AvaliacaoDto) avaliacao);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Avaliacao> listar() {
        return avaliacaoRepository.listar();
    }

    @Override
    public Avaliacao buscarPorId(Integer idAvaliacao) {
        return avaliacaoRepository.buscarPorId(idAvaliacao);
    }

    @Override
    public List<Avaliacao> buscarPorServico(Integer tipoServico) {
        return avaliacaoRepository.buscarPorServico(tipoServico);
    }

    @Override
    public List<Avaliacao> buscarPorExecutor(Integer idUsuExect) {
        return avaliacaoRepository.buscarPorExecutor(idUsuExect);
    }

    @Override
    public List<Avaliacao> buscarPorConferente(Integer idUsuConf) {
        return avaliacaoRepository.buscarPorConferente(idUsuConf);
    }
}
