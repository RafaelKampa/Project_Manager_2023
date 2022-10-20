package com.br.projetoFinal.controller;

import com.br.projetoFinal.service.ParametrosAlvenariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PARAMETROS_ALVENARIA")
public class ParametrosAlvenariaController {

    @Autowired
    ParametrosAlvenariaService parametrosAlvenariaService;


}
