package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;
import com.br.projetoFinal.entity.ParametrosCarpintaria;
import com.br.projetoFinal.service.ParametrosCarpintariaService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.NoSuchElementException;

public class ParametrosCarpintariaController {

    @Autowired
    ParametrosCarpintariaService parametrosCarpintariaService;

    @PostMapping
    public void salvarParametrosAvaliados(@RequestBody ParametrosCarpintariaDto parametrosCarpintariaDto) throws ExcecaoExemplo, SystemException{
        parametrosCarpintariaService.salvarParametrosAvaliados(parametrosCarpintariaDto);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<ParametrosCarpintaria> buscarPorId(@PathVariable("ID") Integer idParametrosCarpintaria) {
        try {
            ParametrosCarpintaria parametrosCarpintaria = parametrosCarpintariaService.buscarPorId(idParametrosCarpintaria);
            return new ResponseEntity<>(parametrosCarpintaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{ID_AVALIACAO}")
    public ResponseEntity<ParametrosCarpintaria> buscarPorAvaliacao(@PathVariable("ID_AVALIACAO") Integer idAvaliacao) {
        try {
            ParametrosCarpintaria parametrosCarpintaria = parametrosCarpintariaService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosCarpintaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ID}")
    public void excluir(@PathVariable("ID") Integer idParametrosCarpintaria) {
        parametrosCarpintariaService.excluir(idParametrosCarpintaria);
    }
}
