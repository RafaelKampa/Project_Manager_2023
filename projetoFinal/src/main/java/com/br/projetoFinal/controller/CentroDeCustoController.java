package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.service.CentroDeCustoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/CENTRO_DE_CUSTO")
public class CentroDeCustoController {

    @Autowired
    CentroDeCustoService centroDeCustoService;

    @PostMapping
    public void salvarNovoCentroDeCusto(@RequestBody CentroDeCustoDto centroDeCustoDto) throws ExcecaoExemplo, SystemException {
        centroDeCustoService.salvarNovoCentroDeCusto(centroDeCustoDto);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<CentroDeCusto> buscarPorId(@PathVariable("ID") Integer idCentroDeCusto) {
        try {
            CentroDeCusto centroDeCusto = centroDeCustoService.buscarPorId(idCentroDeCusto);
            return new ResponseEntity<>(centroDeCusto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{NOME_CENTRO_DE_CUSTO}")
    public ResponseEntity<CentroDeCusto> buscarPorNome(@PathVariable("NOME_CENTRO_DE_CUSTO") String nomeCentroDeCusto) {
        try {
            CentroDeCusto centroDeCusto = centroDeCustoService.buscarPorNome(nomeCentroDeCusto);
            return new ResponseEntity<>(centroDeCusto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ID}")
    public void excluir(@PathVariable("ID") Integer idCentroDeCusto) {
        centroDeCustoService.excluir(idCentroDeCusto);
    }
}
