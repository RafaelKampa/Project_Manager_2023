package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.entity.Avaliacao;
import com.br.projetoFinal.service.AvaliacaoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    AvaliacaoService avaliacaoService;

    @PostMapping("/avaliar")
    public void avaliar(@RequestBody AvaliacaoDto avaliacaoDto) throws ExcecaoExemplo, SystemException {
        avaliacaoService.avaliar(avaliacaoDto);
    }

    @PostMapping("/reavaliar")
    public void reavaliar(@RequestBody AvaliacaoDto avaliacaoDto) throws ExcecaoExemplo, SystemException {
        avaliacaoService.reavaliar(avaliacaoDto);
    }

    @GetMapping
    public List<Avaliacao> listar() {
        return avaliacaoService.listar();
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Avaliacao> buscarPorId(@PathVariable("ID") Integer idAvaliacao) {
        try {
            Avaliacao avaliacao = avaliacaoService.buscarPorId(idAvaliacao);
            return new ResponseEntity<>(avaliacao, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{TIPO_SERVICO}")
    public List<Avaliacao> buscarPorServico(@PathVariable("TIPO_SERVICO") Integer tipoServico) {
        return avaliacaoService.buscarPorServico(tipoServico);
    }

    @GetMapping("/{ID_USU_EXECT}")
    public List<Avaliacao> buscarPorExecutor(@PathVariable("ID_USU_EXECT") Integer idUsuExect) {
        return avaliacaoService.buscarPorExecutor(idUsuExect);
    }

    @GetMapping("/{ID_USU_CONF}")
    public List<Avaliacao> buscarPorConferente(@PathVariable("ID_USU_CONF") Integer idUsuConf) {
        return avaliacaoService.buscarPorConferente(idUsuConf);
    }

}
