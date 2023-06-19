package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.RemuneracaoDto;
import com.br.projetoFinal.dto.ValorProducaoDto;
import com.br.projetoFinal.entity.ValorProducao;
import com.br.projetoFinal.service.ValorProducaoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/valor-producao")
public class ValorProducaoController {

    @Autowired
    ValorProducaoService valorProducaoService;

    @PostMapping("/inserirValorProducao")
    public ResponseEntity<?> inserirValorProducao(@RequestBody ValorProducaoDto valorProducaoDto) throws ExcecaoExemplo, SystemException {
        try {
            valorProducaoService.inserirValorProducao(valorProducaoDto);
            return new ResponseEntity<>(valorProducaoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarValorMensal/{id_usuario}/{mesReferencia}/{anoReferencia}")
    public ResponseEntity<Double> buscarValorMensal(@PathVariable("id_usuario") Integer idUsuario, @PathVariable("mesReferencia") Integer mesReferencia, @PathVariable("anoReferencia") Integer anoReferencia) {
        try {
            Double valor = valorProducaoService.buscarValorMensal(idUsuario, mesReferencia, anoReferencia);
            return new ResponseEntity<>(valor, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarProducaoPorUsuario/{idUsuario}")
    public ResponseEntity<List<ValorProducaoDto>> listarProducaoPorUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            return new ResponseEntity<>(valorProducaoService.listarProducaoPorUsuario(idUsuario), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
