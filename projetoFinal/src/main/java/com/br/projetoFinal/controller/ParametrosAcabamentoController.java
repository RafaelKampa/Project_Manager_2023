package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;
import com.br.projetoFinal.entity.ParametrosAcabamento;
import com.br.projetoFinal.service.ParametrosAcabamentoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/PARAMETROS_ACABAMENTO")
public class ParametrosAcabamentoController {

    @Autowired
    ParametrosAcabamentoService parametrosAcabamentoService;

    @PostMapping
    public void salvarParametrosAvaliados(@RequestBody ParametrosAcabamentoDto parametrosAcabamentoDto) throws ExcecaoExemplo, SystemException, SystemException {
        parametrosAcabamentoService.salvarParametrosAvaliados(parametrosAcabamentoDto);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<ParametrosAcabamento> buscarPorId(@PathVariable("ID") Integer idParametrosAcabamento) {
        try {
            ParametrosAcabamento parametrosAcabamento = parametrosAcabamentoService.buscarPorId(idParametrosAcabamento);
            return new ResponseEntity<>(parametrosAcabamento, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{ID_AVALIACAO}")
    public ResponseEntity<ParametrosAcabamento> buscarPorAvaliacao(@PathVariable("ID_AVALIACAO") Integer idAvaliacao) {
        try {
            ParametrosAcabamento parametrosAcabamento = parametrosAcabamentoService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosAcabamento, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ID}")
    public void excluir(@PathVariable("ID") Integer idParametrosAcabamento) {
        parametrosAcabamentoService.excluir(idParametrosAcabamento);
    }
}
