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
@RequestMapping("/parametros-acabamento")
public class ParametrosAcabamentoController {

    @Autowired
    ParametrosAcabamentoService parametrosAcabamentoService;

    @PostMapping
    public ResponseEntity<?> salvarParametrosAvaliados(@RequestBody ParametrosAcabamentoDto parametrosAcabamentoDto) throws ExcecaoExemplo, SystemException {
        try {
            parametrosAcabamentoService.salvarParametrosAvaliados(parametrosAcabamentoDto);
            return new ResponseEntity<>(parametrosAcabamentoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_parametros_acabamento}")
    public ResponseEntity<ParametrosAcabamentoDto> buscarPorId(@PathVariable("id_parametros_acabamento") Integer idParametrosAcabamento) {
        try {
            ParametrosAcabamentoDto parametrosAcabamentoDto = parametrosAcabamentoService.buscarPorId(idParametrosAcabamento);
            return new ResponseEntity<>(parametrosAcabamentoDto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_avaliacao}")
    public ResponseEntity<ParametrosAcabamentoDto> buscarPorAvaliacao(@PathVariable("id_avaliacao") Integer idAvaliacao) {
        try {
            ParametrosAcabamentoDto parametrosAcabamentoDto = parametrosAcabamentoService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosAcabamentoDto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_parametros_acabamento}")
    public ResponseEntity<?> excluir(@PathVariable("id_parametros_acabamento") Integer idParametrosAcabamento) {
        try {
            parametrosAcabamentoService.excluir(idParametrosAcabamento);
            return new ResponseEntity<>(idParametrosAcabamento, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
