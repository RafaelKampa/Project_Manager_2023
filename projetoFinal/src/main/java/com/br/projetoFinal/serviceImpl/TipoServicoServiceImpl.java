package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.entity.TipoServico;
import com.br.projetoFinal.repository.TipoServicoRepository;
import com.br.projetoFinal.service.TipoServicoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

@Service
public class TipoServicoServiceImpl implements TipoServicoService {

    @Autowired
    private TipoServicoRepository tipoServicoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarNovoServico(TipoServicoDto tipoServicoDto) throws ExcecaoExemplo, SystemException {
        TipoServico tipoServico = mapper.map(tipoServicoDto, TipoServico.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (tipoServicoDto.getNomeServico().equals(null)) {
                throw new ExcecaoExemplo("ERR301", "É necessário informar o nome do serviço.");
            } else if (tipoServicoDto.getUnidadeMedida().equals(null)) {
                throw new ExcecaoExemplo("ERR302", "É necessário informar a unidade de medida do serviço.");
            } else if (tipoServicoDto.getValorUnitario().equals(null)) {
                throw new ExcecaoExemplo("ERR303", "É necessário informar o valor unitário do serviço");
            } else {
                tipoServicoRepository.salvarNovoServico((TipoServicoDto) tipoServico);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoServicoDto> listar() {
        return tipoServicoRepository.listar();
    }

    @Override
    public TipoServicoDto buscarPorId(Integer idTipoServico) {
        return tipoServicoRepository.buscarPorid(idTipoServico);
    }

    @Override
    public List<TipoServicoDto> buscarPorNome(String nomeServico) {
        return tipoServicoRepository.buscarPorNome(nomeServico);
    }

    @Override
    public void excluir(Integer idTipoServico) throws SystemException {
        try {
            utx.begin();
            tipoServicoRepository.excluirServico(idTipoServico);
            utx.commit();
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }
}
