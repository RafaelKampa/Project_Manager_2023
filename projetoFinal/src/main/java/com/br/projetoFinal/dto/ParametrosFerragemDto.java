package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.ParametrosFerragem;

public class ParametrosFerragemDto extends ParametrosFerragem {

    public Integer idParametrosFerragem;
    public Integer idAvaliacao;
    public Boolean espacamento;
    public Double qtdeAco;
    public Boolean distribuicao;
    public String obs;

    public ParametrosFerragemDto() {
    }

    public ParametrosFerragemDto(Integer idParametrosFerragem, Integer idAvaliacao, Boolean espacamento, Double qtdeAco,
                                 Boolean distribuicao) {
        this.idParametrosFerragem = idParametrosFerragem;
        this.idAvaliacao = idAvaliacao;
        this.espacamento = espacamento;
        this.qtdeAco = qtdeAco;
        this.distribuicao = distribuicao;
    }

    public ParametrosFerragemDto(Integer idParametrosFerragem, Integer idAvaliacao, Boolean espacamento, Double qtdeAco,
                                 Boolean distribuicao, String obs) {
        this.idParametrosFerragem = idParametrosFerragem;
        this.idAvaliacao = idAvaliacao;
        this.espacamento = espacamento;
        this.qtdeAco = qtdeAco;
        this.distribuicao = distribuicao;
        this.obs = obs;
    }

    @Override
    public Integer getIdParametrosFerragem() {
        return idParametrosFerragem;
    }

    @Override
    public void setIdParametrosFerragem(Integer idParametrosFerragem) {
        this.idParametrosFerragem = idParametrosFerragem;
    }

    @Override
    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    @Override
    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    @Override
    public Boolean getEspacamento() {
        return espacamento;
    }

    @Override
    public void setEspacamento(Boolean espacamento) {
        this.espacamento = espacamento;
    }

    @Override
    public Double getQtdeAco() {
        return qtdeAco;
    }

    @Override
    public void setQtdeAco(Double qtdeAco) {
        this.qtdeAco = qtdeAco;
    }

    @Override
    public Boolean getDistribuicao() {
        return distribuicao;
    }

    @Override
    public void setDistribuicao(Boolean distribuicao) {
        this.distribuicao = distribuicao;
    }

    @Override
    public String getObs() {
        return obs;
    }

    @Override
    public void setObs(String obs) {
        this.obs = obs;
    }
}
