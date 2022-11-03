package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.ParametrosAcabamento;

public class ParametrosAcabamentoDto extends ParametrosAcabamento {

    public Integer idParametrosAcabamento;
    public Integer idAvaliacao;
    public Double dimensoes;
    public Boolean reguamento;
    public Boolean alisamento;
    public String obs;

    public ParametrosAcabamentoDto() {
    }

    public ParametrosAcabamentoDto(Integer idParametrosAcabamento, Integer idAvaliacao, Double dimensoes,
                                   Boolean reguamento, Boolean alisamento) {
        this.idParametrosAcabamento = idParametrosAcabamento;
        this.idAvaliacao = idAvaliacao;
        this.dimensoes = dimensoes;
        this.reguamento = reguamento;
        this.alisamento = alisamento;
    }

    public ParametrosAcabamentoDto(Integer idParametrosAcabamento, Integer idAvaliacao, Double dimensoes,
                                   Boolean reguamento, Boolean alisamento, String obs) {
        this.idParametrosAcabamento = idParametrosAcabamento;
        this.idAvaliacao = idAvaliacao;
        this.dimensoes = dimensoes;
        this.reguamento = reguamento;
        this.alisamento = alisamento;
        this.obs = obs;
    }

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
