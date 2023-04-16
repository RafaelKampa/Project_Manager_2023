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
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/parametros-alvenaria")
public class ParametrosAlvenariaController {

    @Autowired
    ParametrosAlvenariaService parametrosAlvenariaService;

    @PostMapping("/salvarNovosParametros")
    public ResponseEntity<?> salvarParametrosAvaliados(@RequestBody ParametrosAlvenariaDto parametrosAlvenariaDto) throws ExcecaoExemplo, SystemException, SystemException {
        try {
            parametrosAlvenariaService.salvarParametrosAvaliados(parametrosAlvenariaDto);
            return new ResponseEntity<>(parametrosAlvenariaDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_parametros_alvenaria}")
    public ResponseEntity<ParametrosAlvenariaDto> buscarPorId(@PathVariable("id_parametros_alvenaria") Integer idParametrosAlvenaria) {
        try {
            ParametrosAlvenariaDto parametrosAlvenaria = parametrosAlvenariaService.buscarPorId(idParametrosAlvenaria);
            return new ResponseEntity<>(parametrosAlvenaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_avaliacao}")
    public ResponseEntity<ParametrosAlvenariaDto> buscarPorAvaliacao(@PathVariable("id_avaliacao") Integer idAvaliacao) {
        try {
            ParametrosAlvenariaDto parametrosAlvenaria = parametrosAlvenariaService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosAlvenaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_parametros_alvenaria}")
    public ResponseEntity<?> excluir(@PathVariable("id_parametros_alvenaria") Integer idParametrosAlvenaria) {
        try {
            parametrosAlvenariaService.excluir(idParametrosAlvenaria);
            return new ResponseEntity<>(idParametrosAlvenaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
