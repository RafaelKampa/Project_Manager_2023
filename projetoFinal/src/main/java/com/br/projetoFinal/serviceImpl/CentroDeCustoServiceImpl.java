package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.repository.CentroDeCustoRepository;
import com.br.projetoFinal.service.CentroDeCustoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

@Service
public class CentroDeCustoServiceImpl implements CentroDeCustoService {

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;

    @Override
    public void salvarNovoCentroDeCusto(CentroDeCustoDto centroDeCustoDto) throws ExcecaoExemplo, SystemException {
        CentroDeCusto centroDeCusto = mapper.map(centroDeCustoDto, CentroDeCusto.class);//Utilizado para mapear um DTO para Entity e vice versa
        try {
            utx.begin();
            if (centroDeCustoDto.getNomeCentroDeCusto().equals(null)) {
                throw new ExcecaoExemplo("ERR008", "É necessário informar o Nome do Centro de Custo.");
            } else if (centroDeCustoDto.getEnderecoCentroDeCusto().equals(null)) {
                throw new ExcecaoExemplo("ERR202", "É necessário informar o Endereço do Centro de Custo.");
            } else {
                centroDeCustoRepository.salvarNovoCentroDeCusto((CentroDeCustoDto) centroDeCusto);
                utx.commit();
            }
        } catch (Exception e) {
            utx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<CentroDeCustoDto> listarCentrosDeCusto() {
        return centroDeCustoRepository.listarCentrosDeCusto();
    }

    @Override
    public CentroDeCusto buscarCentroPorId(Integer idCentroDeCusto) {
        return centroDeCustoRepository.buscarCentroPorId(idCentroDeCusto);
    }

    @Override
    public CentroDeCusto buscarPorNome(String nomeCentroDeCusto) {
        return centroDeCustoRepository.buscarPorNome(nomeCentroDeCusto);
    }

    @Override
    public void excluir(Integer idCentroDeCusto) {
        centroDeCustoRepository.excluir(idCentroDeCusto);
    }
}
