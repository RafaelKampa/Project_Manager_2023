package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.TipoServicoDto;
import com.br.projetoFinal.entity.Servico;
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
@RequestMapping("/TIPO_SERVICO_TABLE")
public class TipoServicoController {

    @Autowired
    TipoServicoService tipoServicoService;

    @PostMapping
    public void salvarNovoServico(@RequestBody TipoServicoDto tipoServicoDto) throws ExcecaoExemplo, SystemException {
        tipoServicoService.salvarNovoServico(tipoServicoDto);
    }

    @PostMapping
    public void editarServico(@RequestBody TipoServicoDto tipoServicoDto) throws ExcecaoExemplo, SystemException {
        tipoServicoService.editarServico(tipoServicoDto);
    }

    @GetMapping
    public List<TipoServico> listar() {
        return tipoServicoService.listar();
    }

    @GetMapping("/{ID}")
    public ResponseEntity<TipoServico> buscarPorId(@PathVariable("ID") Integer idTipoServico) {
        try {
            TipoServico tipoServico = tipoServicoService.buscarPorId(idTipoServico);
            return new ResponseEntity<>(tipoServico, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ID}")
    public void excluir(@PathVariable("ID") Integer idTipoServico) {
        tipoServicoService.excluir(idTipoServico);
    }

    @GetMapping("/{NOME_SERVICO}")
    public List<TipoServico> buscarPorNome(@PathVariable("NOME_SERVICO") String nomeServico) {
        return tipoServicoService.buscarPorNome(nomeServico);
    }
}
