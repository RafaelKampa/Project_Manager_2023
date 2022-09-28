package com.br.projetoFinal.controller;


import com.br.projetoFinal.entity.Usuario;
import com.br.projetoFinal.serviceImpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @PostMapping
    public void salvar(@RequestBody Usuario usuario) {
        usuarioService.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public List<Usuario> buscarPorId(@PathVariable("id") Integer id){
        return usuarioService.buscarPorId(id);
    }

    @GetMapping("/{nome}")
    public List<Usuario> buscarPorNome(@PathVariable("nome") String nome){
        return usuarioService.buscarPorNome(nome);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Integer id) {
        usuarioService.excluir(id);
    }

}