package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.serviceImpl.UsuarioServiceImpl;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @PostMapping
    public void salvarUsuario(@RequestBody UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException {
        usuarioService.salvarUsuario(usuarioDto);
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity buscarPorId(@PathVariable("idUsuario") Integer idUsuario) {
        try {
            Usuario usuario = usuarioService.buscarPorId(idUsuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity buscarPorNome(@PathVariable("nome") String nome) {
        try {
            Usuario usuario = usuarioService.buscarPorNome(nome);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idUsuario}")
    public void excluir(@PathVariable("idUsuario") Integer idUsuario) {
        usuarioService.excluir(idUsuario);
    }

}