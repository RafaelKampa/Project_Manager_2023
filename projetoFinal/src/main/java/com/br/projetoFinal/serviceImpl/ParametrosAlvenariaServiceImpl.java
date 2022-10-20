package com.br.projetoFinal.serviceImpl;

import com.br.projetoFinal.repository.ParametrosAlvenariaRepository;
import com.br.projetoFinal.service.ParametrosAlvenariaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.UserTransaction;

@Service
public class ParametrosAlvenariaServiceImpl implements ParametrosAlvenariaService {

    @Autowired
    private ParametrosAlvenariaRepository parametrosAlvenariaRepository;

    @Resource
    ModelMapper mapper;

    @Resource
    private UserTransaction utx;


}
