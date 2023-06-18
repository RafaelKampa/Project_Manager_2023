package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.RemuneracaoDto;
import com.br.projetoFinal.service.RemuneracaoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/remuneracao")
public class RemuneracaoController {

    @Autowired
    RemuneracaoService remuneracaoService;

    @PostMapping("/salvarNovaRemuneracao")
    public ResponseEntity<?> salvarNovaRemuneracao(@RequestBody RemuneracaoDto remuneracaoDto) throws ExcecaoExemplo, SystemException {
        try {
            remuneracaoService.salvarNovaRemuneracao(remuneracaoDto);
            return new ResponseEntity<>(remuneracaoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarUltimaRemuneracaoUsuario/{id_usuario}")
    public ResponseEntity<Double> buscarUltimaRemuneracaoUsuario(@PathVariable("id_usuario") Integer idUsuario) {
        try {
            Double valor = remuneracaoService.buscarUltimaRemuneracaoUsuario(idUsuario);
            return new ResponseEntity<>(valor, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarRemuneracaoPorMes/{id_usuario}/{mesReferencia}/{anoReferencia}")
    public ResponseEntity<Double> buscarRemuneracaoPorMes(@PathVariable("id_usuario") Integer idUsuario,
                                                          @PathVariable("mesReferencia") Integer mesReferencia,
                                                          @PathVariable("anoReferencia") Integer anoReferencia) {
        try {
            Double valor = remuneracaoService.buscarRemuneracaoPorMes(idUsuario, mesReferencia, anoReferencia);
            return new ResponseEntity<>(valor, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
