package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.Avaliacao;

import java.util.Date;

public class AvaliacaoDto extends Avaliacao {
    public Integer idAvaliacao;
    public String tipoServico;
    public Integer idServico;
    public String usuExect;
    public String usuConf;
    public Boolean resultado;
    public Date dataAvaliacao;
    public Date dataReavaliacao;
    public Boolean resultReaval;
    public String obs;
    public String centroDeCusto;
    public String localExecucao;

    public AvaliacaoDto() {
    }

    public AvaliacaoDto(String tipoServico, Integer idServico, String usuExect, String usuConf, Boolean resultado, Date dataAvaliacao) {
        this.tipoServico = tipoServico;
        this.idServico = idServico;
        this.usuExect = usuExect;
        this.usuConf = usuConf;
        this.resultado = resultado;
        this.dataAvaliacao = dataAvaliacao;
    }

    public AvaliacaoDto(Integer id, String tipoServico, Integer idServico, String usuExect, String usuConf,
                        Boolean resultado, Date dataAvaliacao, Date dataReavaliacao, Boolean resultReaval, String obs) {
        this.idAvaliacao = id;
        this.tipoServico = tipoServico;
        this.idServico = idServico;
        this.usuExect = usuExect;
        this.usuConf = usuConf;
        this.resultado = resultado;
        this.dataAvaliacao = dataAvaliacao;
        this.dataReavaliacao = dataReavaliacao;
        this.resultReaval = resultReaval;
        this.obs = obs;
    }

    public AvaliacaoDto(String tipoServico, String centroDeCusto, String localExecucao, Date dataAvaliacao, Date dataReavaliacao, String usuConf,
                        Boolean resultado, Boolean resultReaval, String obs) {
        this.tipoServico = tipoServico;
        this.centroDeCusto = centroDeCusto;
        this.localExecucao = localExecucao;
        this.dataAvaliacao = dataAvaliacao;
        this.dataReavaliacao = dataReavaliacao;
        this.usuConf = usuConf;
        this.resultado = resultado;
        this.resultReaval = resultReaval;
        this.obs = obs;
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
    public String getTipoServico() {
        return tipoServico;
    }

    @Override
    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
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
    public String getUsuExect() {
        return usuExect;
    }

    @Override
    public void setUsuExect(String usuExect) {
        this.usuExect = usuExect;
    }

    @Override
    public String getUsuConf() {
        return usuConf;
    }

    @Override
    public void setUsuConf(String usuConf) {
        this.usuConf = usuConf;
    }

    @Override
    public Boolean getResultado() {
        return resultado;
    }

    @Override
    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    @Override
    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    @Override
    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    @Override
    public Date getDataReavaliacao() {
        return dataReavaliacao;
    }

    @Override
    public void setDataReavaliacao(Date dataReavaliacao) {
        this.dataReavaliacao = dataReavaliacao;
    }

    @Override
    public Boolean getResultReaval() {
        return resultReaval;
    }

    @Override
    public void setResultReaval(Boolean resultReaval) {
        this.resultReaval = resultReaval;
    }

    @Override
    public String getObs() {
        return obs;
    }

    @Override
    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(String centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public String getLocalExecucao() {
        return localExecucao;
    }

    public void setLocalExecucao(String localExecucao) {
        this.localExecucao = localExecucao;
    }
}
