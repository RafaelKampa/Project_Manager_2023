package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.repositoryImpl.AvaliacaoRepositoryImpl;
import com.br.projetoFinal.service.AvaliacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepositoryImpl avaliacaoRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;
}
