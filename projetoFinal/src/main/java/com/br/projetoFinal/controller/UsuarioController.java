package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.service.UsuarioService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Operation(summary = "QUALQUER COISA") //TODO: Corrigir Swagger
    @PostMapping("/salvarUsuario")
    public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException {
        try {
            usuarioService.salvarUsuario(usuarioDto);
            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<UsuarioDto>> listar() {
        try {
            return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarConferentes")
    public ResponseEntity<List<UsuarioDto>> buscarConferentes() {
        try {
            return new ResponseEntity<>(usuarioService.buscarConferentes(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarExecutores")
    public ResponseEntity<List<UsuarioDto>> buscarExecutores() {
        try {
            return new ResponseEntity<>(usuarioService.buscarExecutores(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorId/{id_usuario}")
    public ResponseEntity buscarPorId(@PathVariable("id_usuario") Integer idUsuario) {
        try {
            UsuarioDto usuario = usuarioService.buscarPorId(idUsuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorNome/{nomeUsuario}")
    public ResponseEntity buscarPorNome(@PathVariable("nomeUsuario") String nomeUsuario) {
        try {
            UsuarioDto usuario = usuarioService.buscarPorNome(nomeUsuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/excluir/{id_usuario}")
    public ResponseEntity<?> excluir(@PathVariable("id_usuario") Integer idUsuario) throws SystemException {
        try {
            usuarioService.excluir(idUsuario);
            return new ResponseEntity<>(idUsuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarUltimoId")
    public ResponseEntity<Integer> buscarUltimoId() {
        try {
            Integer ultimoId = usuarioService.buscarUltimoId();
            return new ResponseEntity<>(ultimoId, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}