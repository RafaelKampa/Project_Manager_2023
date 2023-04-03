package com.br.projetoFinal.entity;



import com.br.projetoFinal.dto.ParametrosFerragemDto;

import javax.persistence.*;

@Entity
@Table(name = "PARAMETROS_FERRAGEM")
public class ParametrosFerragem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
