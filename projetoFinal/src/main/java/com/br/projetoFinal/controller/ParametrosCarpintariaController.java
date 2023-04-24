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

@RestController
@RequestMapping("/parametros-carpintaria")
public class ParametrosCarpintariaController {

    @Autowired
    ParametrosCarpintariaService parametrosCarpintariaService;

    @PostMapping
    public ResponseEntity<?> salvarParametrosAvaliados(@RequestBody ParametrosCarpintariaDto parametrosCarpintariaDto) throws ExcecaoExemplo, SystemException{
        try {
            parametrosCarpintariaService.salvarParametrosAvaliados(parametrosCarpintariaDto);
            return new ResponseEntity<>(parametrosCarpintariaDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_parametros_carpintaria}")
    public ResponseEntity<ParametrosCarpintariaDto> buscarPorId(@PathVariable("id_parametros_carpintaria") Integer idParametrosCarpintaria) {
        try {
            ParametrosCarpintariaDto parametrosCarpintariaDto = parametrosCarpintariaService.buscarPorId(idParametrosCarpintaria);
            return new ResponseEntity<>(parametrosCarpintariaDto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_avaliacao}")
    public ResponseEntity<ParametrosCarpintariaDto> buscarPorAvaliacao(@PathVariable("id_avaliacao") Integer idAvaliacao) {
        try {
            ParametrosCarpintariaDto parametrosCarpintariaDto = parametrosCarpintariaService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosCarpintariaDto, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_parametros_carpintaria}")
    public ResponseEntity<?> excluir(@PathVariable("id_parametros_carpintaria") Integer idParametrosCarpintaria) throws SystemException {
        try {
            parametrosCarpintariaService.excluir(idParametrosCarpintaria);
            return new ResponseEntity<>(idParametrosCarpintaria, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
