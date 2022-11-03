package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.ParametrosAlvenariaDto;
import com.br.projetoFinal.entity.ParametrosAlvenaria;
import com.br.projetoFinal.service.ParametrosAlvenariaService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/PARAMETROS_ALVENARIA")
public class ParametrosAlvenariaController {

    @Autowired
    ParametrosAlvenariaService parametrosAlvenariaService;

    @PostMapping
    public void salvarParametrosAvaliados(@RequestBody ParametrosAlvenariaDto parametrosAlvenariaDto) throws ExcecaoExemplo, SystemException, SystemException {
        parametrosAlvenariaService.salvarParametrosAvaliados(parametrosAlvenariaDto);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<ParametrosAlvenaria> buscarPorId(@PathVariable("ID") Integer idParametrosAlvenaria) {
        try {
            ParametrosAlvenaria parametrosAlvenaria = parametrosAlvenariaService.buscarPorId(idParametrosAlvenaria);
            return new ResponseEntity<>(parametrosAlvenaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{ID_AVALIACAO}")
    public ResponseEntity<ParametrosAlvenaria> buscarPorAvaliacao(@PathVariable("ID_AVALIACAO") Integer idAvaliacao) {
        try {
            ParametrosAlvenaria parametrosAlvenaria = parametrosAlvenariaService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosAlvenaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ID}")
    public void excluir(@PathVariable("ID") Integer idParametrosAlvenaria) {
        parametrosAlvenariaService.excluir(idParametrosAlvenaria);
    }
}
