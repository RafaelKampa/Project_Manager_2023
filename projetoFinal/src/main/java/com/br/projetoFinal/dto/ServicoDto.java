package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.Servico;
import java.util.Date;

public class ServicoDto extends Servico {
    public Integer idServico;
    public String tipoServico;
    public Double valorUnitario;
    public Double dimensao;
    public String localExecucao;
    public String executor;
    public String conferente;
    public Date dataInicio;
    public Date dataFinal;
    public Double valorTotal;
    public String obs;
    public int situacao;

    public ServicoDto() {
    }

    public ServicoDto(Integer idServico, String tipoServico, Double valorUnitario, Double dimensao, String localExecucao,
                      String executor, String conferente, Date dataInicio, Date dataFinal, Double valorTotal, String obs,
                      int situacao) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.valorTotal = valorTotal;
        this.obs = obs;
        this.situacao = situacao;
    }

    public ServicoDto(String tipoServico, Double valorUnitario, Double dimensao, String localExecucao, String executor,
                      String conferente, Date dataInicio, int situacao) {
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
        this.situacao = situacao;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getDimensao() {
        return dimensao;
    }

    public void setDimensao(Double dimensao) {
        this.dimensao = dimensao;
    }

    public String getLocalExecucao() {
        return localExecucao;
    }

    public void setLocalExecucao(String localExecucao) {
        this.localExecucao = localExecucao;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getConferente() {
        return conferente;
    }

    public void setConferente(String conferente) {
        this.conferente = conferente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
