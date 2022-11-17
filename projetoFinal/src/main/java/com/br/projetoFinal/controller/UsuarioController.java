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

    @GetMapping("/buscarPorId/{ID}")
    public ResponseEntity buscarPorId(@PathVariable("ID") Integer idUsuario) {
        try {
            Usuario usuario = usuarioService.buscarPorId(idUsuario);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscarPorNome/{NOME}")
    public ResponseEntity buscarPorNome(@PathVariable("NOME") String nome) {
        try {
            Usuario usuario = usuarioService.buscarPorNome(nome);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/excluir/{ID}")
    public void excluir(@PathVariable("ID") Integer idUsuario) {
        usuarioService.excluir(idUsuario);
    }

    @GetMapping("/logar/{LOGIN}/{SENHA}")
    public ResponseEntity logar(@PathVariable("LOGIN") String login, @PathVariable("SENHA") String senha) {
        try {
            Usuario usuario = usuarioService.logar(login, senha);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}