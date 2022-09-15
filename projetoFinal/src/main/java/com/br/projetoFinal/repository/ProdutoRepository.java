package com.br.projetoFinal.repository;

import com.br.projetoFinal.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
