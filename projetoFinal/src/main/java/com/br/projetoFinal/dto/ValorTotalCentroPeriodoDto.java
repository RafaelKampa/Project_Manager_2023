package com.br.projetoFinal.dto;

public class ValorTotalCentroPeriodoDto {
    public String centroDeCusto;
    public Integer mesPeriodo;
    public Integer anoPeriodo;
    public String tipoServico;
    public Double valorServico;

    public ValorTotalCentroPeriodoDto(String centroDeCusto, Integer mesPeriodo, Integer anoPeriodo, String tipoServico, Double valorServico) {
        this.centroDeCusto = centroDeCusto;
        this.mesPeriodo = mesPeriodo;
        this.anoPeriodo = anoPeriodo;
        this.tipoServico = tipoServico;
        this.valorServico = valorServico;
    }

    public String getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(String centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public Integer getMesPeriodo() {
        return mesPeriodo;
    }

    public void setMesPeriodo(Integer mesPeriodo) {
        this.mesPeriodo = mesPeriodo;
    }

    public Integer getAnoPeriodo() {
        return anoPeriodo;
    }

    public void setAnoPeriodo(Integer anoPeriodo) {
        this.anoPeriodo = anoPeriodo;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }
}
