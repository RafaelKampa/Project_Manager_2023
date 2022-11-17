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

    @PostMapping
    public void salvarParametrosAvaliados(@RequestBody ParametrosFerragemDto parametrosFerragemServiceDto) throws ExcecaoExemplo, SystemException {
        parametrosFerragemService.salvarParametrosAvaliados(parametrosFerragemServiceDto);
    }

    @GetMapping("/{ID}")
    public ResponseEntity<ParametrosFerragem> buscarPorId(@PathVariable("ID") Integer idParametrosFerragem) {
        try {
            ParametrosFerragem parametrosFerragem = parametrosFerragemService.buscarPorId(idParametrosFerragem);
            return new ResponseEntity<>(parametrosFerragem, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{ID_AVALIACAO}")
    public ResponseEntity<ParametrosFerragem> buscarPorAvaliacao(@PathVariable("ID_AVALIACAO") Integer idAvaliacao) {
        try {
            ParametrosFerragem parametrosFerragem = parametrosFerragemService.buscarPorAvaliacao(idAvaliacao);
            return new ResponseEntity<>(parametrosFerragem, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ID}")
    public void excluir(@PathVariable("ID") Integer idParametrosFerragem) {
        parametrosFerragemService.excluir(idParametrosFerragem);
    }
}