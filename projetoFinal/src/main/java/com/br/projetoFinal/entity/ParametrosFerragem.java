package com.br.projetoFinal.entity;


import com.br.projetoFinal.dto.ParametrosAlvenariaDto;

import javax.persistence.*;

@Entity
@Table(name = "PARAMETROS_FERRAGEM")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ParametrosFerragem.dtoMapping", classes = {
                @ConstructorResult(targetClass = ParametrosAlvenariaDto.class,
                        columns ={
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "ID_AVALIACAO", type = Integer.class),
                                @ColumnResult(name = "ESPACAMENTO", type = Boolean.class),
                                @ColumnResult(name = "QTDE_ACO", type = Double.class),
                                @ColumnResult(name = "DISTRIBUICAO", type = Boolean.class),
                                @ColumnResult(name = "OBS", type = String.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "ParametrosFerragem.buscarPorAvaliacao", query = "SELECT * FROM PARAMETROS_FERRAGEM WHERE ID_AVALIACAO = :ID_AVALIACAO", resultSetMapping = "ParametrosFerragem.dtoMapping"),
        @NamedNativeQuery(name = "ParametrosFerragem.buscarPorId", query = "SELECT * FROM PARAMETROS_FERRAGEM WHERE ID = :ID", resultSetMapping = "ParametrosFerragem.dtoMapping")
})
public class ParametrosFerragem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idParametrosFerragem;

    @Column(name = "ID_AVALIACAO", nullable = false)
    private Integer idAvaliacao;

    @Column(name = "ESPACAMENTO", nullable = false)
    private Boolean espacamento;

    @Column(name = "QTDE_ACO", nullable = false)
    private Double qtdeAco;

    @Column(name = "DISTRIBUICAO", nullable = false)
    private Boolean distribuicao;

    @Column(name = "OBS")
    private String obs;

    public Integer getIdParametrosFerragem() {
        return idParametrosFerragem;
    }

    public void setIdParametrosFerragem(Integer idParametrosFerragem) {
        this.idParametrosFerragem = idParametrosFerragem;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Boolean getEspacamento() {
        return espacamento;
    }

    public void setEspacamento(Boolean espacamento) {
        this.espacamento = espacamento;
    }

    public Double getQtdeAco() {
        return qtdeAco;
    }

    public void setQtdeAco(Double qtdeAco) {
        this.qtdeAco = qtdeAco;
    }

    public Boolean getDistribuicao() {
        return distribuicao;
    }

    public void setDistribuicao(Boolean distribuicao) {
        this.distribuicao = distribuicao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
