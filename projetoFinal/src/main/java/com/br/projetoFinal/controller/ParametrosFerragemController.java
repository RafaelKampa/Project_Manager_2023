package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.ParametrosFerragemDto;
import com.br.projetoFinal.entity.ParametrosFerragem;
import com.br.projetoFinal.service.ParametrosFerragemService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/parametros-ferragem")
public class ParametrosFerragemController {

    @Autowired
    ParametrosFerragemService parametrosFerragemService;

    @PostMapping("/salvarNovosParametros")
    public ResponseEntity<?> salvarParametrosAvaliados(@RequestBody ParametrosFerragemDto parametrosFerragemServiceDto) throws ExcecaoExemplo, SystemException {
        try {
            parametrosFerragemService.salvarParametrosAvaliados(parametrosFerragemServiceDto);
            return new ResponseEntity<>(parametrosFerragemServiceDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_parametros_ferragem}")
    public ResponseEntity<ParametrosFerragemDto> buscarPorId(@PathVariable("id_parametros_ferragem") Integer idParametrosFerragem) {
        try {
            ParametrosFerragemDto parametrosFerragem = parametrosFerragemService.buscarPorId(idParametrosFerragem);
            return new ResponseEntity<>(parametrosFerragem, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_avaliacao}")
    public ResponseEntity<ParametrosFerragemDto> buscarPorAvaliacao(@PathVariable("id_avaliacao") Integer idAvaliacao) {
        try {
            ParametrosFerragemDto parametrosFerragem = parametrosFerragemService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosFerragem, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_parametros_ferragem}")
    public ResponseEntity<?> excluir(@PathVariable("id_parametros_ferragem") Integer idParametrosFerragem) throws SystemException {
        try {
            parametrosFerragemService.excluir(idParametrosFerragem);
            return new ResponseEntity<>(idParametrosFerragem, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}