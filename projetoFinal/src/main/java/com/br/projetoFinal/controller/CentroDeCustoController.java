package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.CentroDeCustoDto;
import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.CentroDeCusto;
import com.br.projetoFinal.service.CentroDeCustoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/centroDeCusto")
public class CentroDeCustoController {

    @Autowired
    CentroDeCustoService centroDeCustoService;

    @PostMapping("/salvarNovoCentroDeCusto")
    public ResponseEntity<?> salvarNovoCentroDeCusto(@RequestBody CentroDeCustoDto centroDeCustoDto) throws ExcecaoExemplo, SystemException {
        try {
            centroDeCustoService.salvarNovoCentroDeCusto(centroDeCustoDto);
            return new ResponseEntity<>(centroDeCustoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarCentrosDeCusto")
    public ResponseEntity<List<CentroDeCustoDto>> listarCentrosDeCusto() {
        try {
            return new ResponseEntity<>(centroDeCustoService.listarCentrosDeCusto(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarCentroPorId/{id_centro_de_custo}")
    public ResponseEntity<CentroDeCustoDto> buscarCentroPorId(@PathVariable("id_centro_de_custo") Integer idCentroDeCusto) {
        try {
            return new ResponseEntity<>(centroDeCustoService.buscarCentroPorId(idCentroDeCusto), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nome_centro_de_custo}")
    public ResponseEntity<CentroDeCustoDto> buscarPorNome(@PathVariable("nome_centro_de_custo") String nomeCentroDeCusto) {
        try {
            CentroDeCustoDto centroDeCusto = centroDeCustoService.buscarPorNome(nomeCentroDeCusto);
            return new ResponseEntity<>(centroDeCusto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_centro_de_custo}")
    public ResponseEntity<?> excluir(@PathVariable("id_centro_de_custo") Integer idCentroDeCusto) throws SystemException {
        try {
            centroDeCustoService.excluir(idCentroDeCusto);
            return new ResponseEntity<>(idCentroDeCusto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
