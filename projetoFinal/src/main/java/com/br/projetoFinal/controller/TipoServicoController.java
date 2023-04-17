package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.entity.TipoServico;
import com.br.projetoFinal.service.TipoServicoService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tipo-servico-table")
public class TipoServicoController {

    @Autowired
    TipoServicoService tipoServicoService;

    @PostMapping
    public ResponseEntity<?> salvarNovoServico(@RequestBody TipoServicoDto tipoServicoDto) throws ExcecaoExemplo, SystemException {
        try {
            tipoServicoService.salvarNovoServico(tipoServicoDto);
            return new ResponseEntity<>(tipoServicoDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarTiposServicos")
    public ResponseEntity<List<TipoServicoDto>> listar() {
        try {
            return new ResponseEntity<>(tipoServicoService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id_tipo_servico}")
    public ResponseEntity<TipoServicoDto> buscarPorId(@PathVariable("id_tipo_servico") Integer idTipoServico) {
        try {
            TipoServicoDto tipoServico = tipoServicoService.buscarPorId(idTipoServico);
            return new ResponseEntity<>(tipoServico, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nome_servico}")
    public ResponseEntity<List<TipoServicoDto>> buscarPorNome(@PathVariable("nome_servico") String nomeServico) {
        try {
            return new ResponseEntity<>(tipoServicoService.buscarPorNome(nomeServico), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id_tipo_servico}")
    public ResponseEntity<?> excluir(@PathVariable("id_tipo_servico") Integer idTipoServico) {
        try {
            tipoServicoService.excluir(idTipoServico);
            return new ResponseEntity<>(idTipoServico, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
