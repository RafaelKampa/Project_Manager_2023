package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.dto.ReavaliacaoDto;
import com.br.projetoFinal.entity.Avaliacao;
import com.br.projetoFinal.service.AvaliacaoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @PostMapping("/avaliar")
    public ResponseEntity<?> avaliar(@RequestBody AvaliacaoDto avaliacaoDto) throws ExcecaoExemplo, SystemException {
        try {
            avaliacaoService.avaliar(avaliacaoDto);
            return new ResponseEntity<>(avaliacaoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reavaliar")
    public ResponseEntity<?> reavaliar(@RequestBody ReavaliacaoDto reavaliacaoDto) throws ExcecaoExemplo, SystemException {
        try {
            avaliacaoService.reavaliar(reavaliacaoDto);
            return new ResponseEntity<>(reavaliacaoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AvaliacaoDto>> listar() {
        try {
            return new ResponseEntity<>(avaliacaoService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>((List<AvaliacaoDto>) null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorId/{id_avaliacao}")
    public ResponseEntity<AvaliacaoDto> buscarPorId(@PathVariable("id_avaliacao") Integer idAvaliacao) {
        try {
            AvaliacaoDto avaliacao = avaliacaoService.buscarPorId(idAvaliacao);
            return new ResponseEntity<>(avaliacao, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorServico/{tipo_servico}")
    public ResponseEntity<List<AvaliacaoDto>> buscarPorServico(@PathVariable("tipo_servico") Integer tipoServico) {
        try {
            return new ResponseEntity<>(avaliacaoService.buscarPorServico(tipoServico), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorExecutor/{id_usu_exect}")
    public ResponseEntity<List<AvaliacaoDto>> buscarPorExecutor(@PathVariable("id_usu_exect") Integer idUsuExect) {
        try {
            return new ResponseEntity<>(avaliacaoService.buscarPorExecutor(idUsuExect), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorConferente/{id_usu_conf}")
    public ResponseEntity<List<AvaliacaoDto>> buscarPorConferente(@PathVariable("id_usu_conf") Integer idUsuConf) {
        try {
            return new ResponseEntity<>(avaliacaoService.buscarPorConferente(idUsuConf), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarUltimoId")
    public ResponseEntity<Integer> buscarUltimoId() {
        try {
            Integer ultimoId = avaliacaoService.buscarUltimoId();
            return new ResponseEntity<>(ultimoId, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
