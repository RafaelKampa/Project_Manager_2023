package com.br.projetoFinal.controller;

import com.br.projetoFinal.dto.UsuarioDto;
import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.service.UsuarioService;
import com.br.projetoFinal.util.excecao.ExcecaoExemplo;
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

    @PostMapping("/salvarUsuario")
    public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioDto usuarioDto) throws ExcecaoExemplo, SystemException {
        usuarioService.salvarUsuario(usuarioDto);
        return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
    }

    @GetMapping("/listarUsuarios")
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/buscarConferentes")
    public List<Usuario> buscarConferentes() {
        return usuarioService.buscarConferentes();
    }

    @GetMapping("/buscarExecutores")
    public List<Usuario> buscarExecutores() {
        return usuarioService.buscarExecutores();
    }

    @GetMapping("/buscarPorId/{id_usuario}")
    public ResponseEntity buscarPorId(@PathVariable("id_usuario") Integer idUsuario) {
        try {
            Usuario usuario = usuarioService.buscarPorId(idUsuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorNome/{login}")
    public ResponseEntity buscarPorNome(@PathVariable("login") String login) {
        try {
            Usuario usuario = usuarioService.buscarPorNome(login);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/excluir/{id_usuario}")
    public void excluir(@PathVariable("id_usuario") Integer idUsuario) {
        usuarioService.excluir(idUsuario);
    }

}