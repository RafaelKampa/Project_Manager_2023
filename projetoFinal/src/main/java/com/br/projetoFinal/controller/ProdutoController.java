package com.br.projetoFinal.controller;

import com.br.projetoFinal.entity.Produto;
import com.br.projetoFinal.serviceImpl.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoServiceImpl produtoService;

    @PostMapping
    public void salvar(@RequestBody Produto produto) {
        produtoService.salvar(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable("id") Integer id){
        return produtoService.buscarPorId(id);
    }
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Integer id) {
        produtoService.excluir(id);
    }

}