package com.br.projetoFinal.dto;

import java.util.Date;

public class ProducaoMensalFuncionarioDto {
    public String centroDeCusto;
    public String tipoServico;
    public String localExecucao;
    public Date dataFinal;
    public Double valorTotal;

    public ProducaoMensalFuncionarioDto(String centroDeCusto, String tipoServico, String localExecucao, Date dataFinal, Double valorTotal) {
        this.centroDeCusto = centroDeCusto;
        this.tipoServico = tipoServico;
        this.localExecucao = localExecucao;
        this.dataFinal = dataFinal;
        this.valorTotal = valorTotal;
    }

    public String getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(String centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getLocalExecucao() {
        return localExecucao;
    }

    public void setLocalExecucao(String localExecucao) {
        this.localExecucao = localExecucao;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

