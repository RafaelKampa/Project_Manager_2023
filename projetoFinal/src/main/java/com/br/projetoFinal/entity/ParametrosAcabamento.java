package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;

import javax.persistence.*;

@Entity
@Table(name = "PARAMETROS_ACABAMENTO")
public class ParametrosAcabamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParametrosAcabamento;

    @Column(name = "ID_AVALIACAO", nullable = false)
    private Integer idAvaliacao;

    @Column(name = "DIMENSOES", nullable = false)
    private Double dimensoes;

    @Column(name = "REGUAMENTO", nullable = false)
    private Boolean reguamento;

    @Column(name = "ALISAMENTO", nullable = false)
    private Boolean alisamento;

    @Column(name = "OBS")
    private String obs;

    public Integer getIdParametrosAcabamento() {
        return idParametrosAcabamento;
    }

    public void setIdParametrosAcabamento(Integer idParametrosAcabamento) {
        this.idParametrosAcabamento = idParametrosAcabamento;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Double getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(Double dimensoes) {
        this.dimensoes = dimensoes;
    }

    public Boolean getReguamento() {
        return reguamento;
    }

    public void setReguamento(Boolean reguamento) {
        this.reguamento = reguamento;
    }

    public Boolean getAlisamento() {
        return alisamento;
    }

    public void setAlisamento(Boolean alisamento) {
        this.alisamento = alisamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
