package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.service.ServicoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.SystemException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @PostMapping("/salvarServico")
    public ResponseEntity<?> salvarNovoServico(@RequestBody ServicoDto servicoDto) throws ExcecaoExemplo, SystemException {
        try {
            servicoService.salvarNovoServico(servicoDto);
            return new ResponseEntity<>(servicoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarServicos")
    public ResponseEntity<List<ServicoDto>> listar() {
        try {
            return new ResponseEntity<>(servicoService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarAguardandoAvaliacao")
    public ResponseEntity<List<ServicoDto>> listarAguardandoAvaliacao() {
        try {
            return new ResponseEntity<>(servicoService.listarAguardandoAvaliacao(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarAvaliados")
    public ResponseEntity<List<ServicoDto>> listarAvaliados() {
        try {
            return new ResponseEntity<>(servicoService.listarAvaliados(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorId/{id_servico}")
    public ResponseEntity<List<ServicoDto>> buscarPorId(@PathVariable("id_servico") Integer idServico) {
        try {
            return new ResponseEntity<>(servicoService.buscarPorId(idServico), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{tipo_servico}")
    public ResponseEntity<List<ServicoDto>> buscarPorNome(@PathVariable("tipo_servico") String tipoServico) {
        try {
            return new ResponseEntity<>(servicoService.buscarPorServico(tipoServico), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_servico}")
    public ResponseEntity<?> excluir(@PathVariable("id_servico") Integer idServico) {
        try {
            servicoService.excluir(idServico);
            return new ResponseEntity<>(idServico, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}