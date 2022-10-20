package com.br.projetoFinal.entity;

import javax.persistence.*;

@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "ID_SERVICO", nullable = false, unique = false)
    private Integer idServico;

    @Column(name = "ID_USUARIO", nullable = false, unique = false)
    private Integer idUsuario;


}
