package com.br.projetoFinal.controller;

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

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/servico")

public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Servico servico) throws ExcecaoExemplo {
        servico = servicoService.salvar(servico);
        return new ResponseEntity<>(servico, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Servico> listar() {
        return servicoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable("id") Integer id) {
        try {
            Servico servico = servicoService.buscarPorId(id);
            return new ResponseEntity<>(servico, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Integer id) {
        servicoService.excluir(id);
    }

}