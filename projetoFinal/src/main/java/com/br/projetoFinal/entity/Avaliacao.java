package com.br.projetoFinal.entity;

import javax.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "idServico", nullable = false, unique = false)
    private Integer idServico;

    @Column(name = "idUsuario", nullable = false, unique = false)
    private Integer idUsuario;


}
