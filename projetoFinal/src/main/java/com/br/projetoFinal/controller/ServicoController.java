package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.dto.ValorTotalCentroPeriodoDto;
import com.br.projetoFinal.entity.Servico;
import com.br.projetoFinal.service.ServicoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import javax.ws.rs.core.Response;
import java.util.Date;
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

    @GetMapping("/servicosAguardandoReaval")
    public ResponseEntity<List<ServicoDto>> servicosAguardandoReaval() {
        try {
            return new ResponseEntity<>(servicoService.servicosAguardandoReaval(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorId/{id_servico}")
    public ResponseEntity<ServicoDto> buscarPorId(@PathVariable("id_servico") Integer idServico) {
        try {
            return new ResponseEntity<>(servicoService.buscarPorId(idServico), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorNome/{tipo_servico}")
    public ResponseEntity<List<ServicoDto>> buscarPorNome(@PathVariable("tipo_servico") String tipoServico) {
        try {
            return new ResponseEntity<>(servicoService.buscarPorServico(tipoServico), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/excluir/{id_servico}")
    public ResponseEntity<?> excluir(@PathVariable("id_servico") Integer idServico) throws SystemException {
        try {
            servicoService.excluir(idServico);
            return new ResponseEntity<>(idServico, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/concluirServico/{idServico}/{indConcluido}/{conferente}")
    public ResponseEntity<?> concluirServico(@PathVariable("idServico") Integer idServico,@PathVariable("indConcluido") Boolean indConcluido, @PathVariable("conferente") String conferente) throws ExcecaoExemplo, SystemException {
        try {
            servicoService.concluirServico(idServico, indConcluido, conferente);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarValorTotalPorCentro/{centroDeCusto}/{mesReferencia}/{anoReferencia}")
    public ResponseEntity<List<ValorTotalCentroPeriodoDto>> buscarValorTotalPorCentro(@PathVariable("centroDeCusto") String centroDeCusto,
                                                                                      @PathVariable("mesReferencia") Integer mesReferencia,
                                                                                      @PathVariable("anoReferencia") Integer anoReferencia) throws ExcecaoExemplo, SystemException {
        try {
            return new ResponseEntity<>(servicoService.buscarValorTotalPorCentro(centroDeCusto, mesReferencia, anoReferencia), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}