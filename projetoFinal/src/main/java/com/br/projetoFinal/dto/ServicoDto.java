package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.Servico;
import java.util.Date;

public class ServicoDto extends Servico {
    public Integer idServico;
    public String tipoServico;
    public Double valorUnitario;
    public Double dimensao;
    public String unidadeMedida;
    public String centroDeCusto;
    public String localExecucao;
    public String executor;
    public String conferente;
    public Date dataInicio;
    public Date previsaoTermino;
    public Date dataFinal;
    public Double valorTotal;
    public String obs;
    public Integer idAvaliacao;
    private Boolean indConcluido;

    public ServicoDto() {
    }

    public ServicoDto(Integer idServico, String tipoServico, Double valorUnitario, Double dimensao, String unidadeMedida, String centroDeCusto,
                      String localExecucao, String executor, String conferente, Date dataInicio, Date previsaoTermino, Date dataFinal,
                      Double valorTotal, String obs, Boolean indConcluido) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.unidadeMedida = unidadeMedida;
        this.centroDeCusto = centroDeCusto;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
        this.previsaoTermino = previsaoTermino;
        this.dataFinal = dataFinal;
        this.valorTotal = valorTotal;
        this.obs = obs;
        this.indConcluido = indConcluido;
    }

    public ServicoDto(Integer idServico, String tipoServico, Double valorUnitario, Double dimensao, String unidadeMedida, String centroDeCusto,
                      String localExecucao, String executor, String conferente, Date dataInicio, Date previsaoTermino, Date dataFinal,
                      Double valorTotal, String obs, Boolean indConcluido, Integer idAvaliacao) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.unidadeMedida = unidadeMedida;
        this.centroDeCusto = centroDeCusto;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
        this.previsaoTermino = previsaoTermino;
        this.dataFinal = dataFinal;
        this.valorTotal = valorTotal;
        this.obs = obs;
        this.indConcluido = indConcluido;
        this.idAvaliacao = idAvaliacao;
    }

    @Override
    public Integer getIdServico() {
        return idServico;
    }

    @Override
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    @Override
    public String getTipoServico() {
        return tipoServico;
    }

    @Override
    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    @Override
    public Double getValorUnitario() {
        return valorUnitario;
    }

    @Override
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public Double getDimensao() {
        return dimensao;
    }

    @Override
    public void setDimensao(Double dimensao) {
        this.dimensao = dimensao;
    }

    @Override
    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    @Override
    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public String getCentroDeCusto() {
        return centroDeCusto;
    }

    @Override
    public void setCentroDeCusto(String centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    @Override
    public String getLocalExecucao() {
        return localExecucao;
    }

    @Override
    public void setLocalExecucao(String localExecucao) {
        this.localExecucao = localExecucao;
    }

    @Override
    public String getExecutor() {
        return executor;
    }

    @Override
    public void setExecutor(String executor) {
        this.executor = executor;
    }

    @Override
    public String getConferente() {
        return conferente;
    }

    @Override
    public void setConferente(String conferente) {
        this.conferente = conferente;
    }

    @Override
    public Date getDataInicio() {
        return dataInicio;
    }

    @Override
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public Date getPrevisaoTermino() {
        return previsaoTermino;
    }

    @Override
    public void setPrevisaoTermino(Date previsaoTermino) {
        this.previsaoTermino = previsaoTermino;
    }

    @Override
    public Date getDataFinal() {
        return dataFinal;
    }

    @Override
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public Double getValorTotal() {
        return valorTotal;
    }

    @Override
    public void setValorTotal() {
        this.valorTotal = this.getDimensao() * this.getValorUnitario();
    }

    @Override
    public String getObs() {
        return obs;
    }

    @Override
    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public Boolean getIndConcluido() {
        return indConcluido;
    }

    public void setIndConcluido(Boolean indConcluido) {
        this.indConcluido = indConcluido;
    }
}
