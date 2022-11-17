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

    @PostMapping("/salvar-servico")
    public void salvarNovoServico(@RequestBody ServicoDto servicoDto) throws ExcecaoExemplo, SystemException {
        servicoService.salvarNovoServico(servicoDto);
    }

    @GetMapping
    public List<Servico> listar() {
        return servicoService.listar();
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable("ID") Integer idServico) {
        try {
            Servico servico = servicoService.buscarPorId(idServico);
            return new ResponseEntity<>(servico, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ID}")
    public void excluir(@PathVariable("ID") Integer idServico) {
        servicoService.excluir(idServico);
    }

    @GetMapping("/{TIPO_SERVICO}")
    public List<Servico> buscarPorNome(@PathVariable("TIPO_SERVICO") String tipoServico) {
        return servicoService.buscarPorServico(tipoServico);
    }
}