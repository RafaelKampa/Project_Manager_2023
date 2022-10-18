package com.br.projetoFinal.controller;

import com.br.projetoFinal.serviceImpl.AvaliacaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    AvaliacaoServiceImpl avaliacaoService;



}
