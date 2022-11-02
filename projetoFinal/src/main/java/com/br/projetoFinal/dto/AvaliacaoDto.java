package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.Avaliacao;

import java.util.Date;

public class AvaliacaoDto extends Avaliacao {
    public Integer idAvaliacao;
    public Integer tipoServico;
    public Integer idServico;
    public Integer idUsuExect;
    public Integer idUsuConf;
    public Boolean resultado;
    public Date dataAvaliacao;
    public Date dataReavaliacao;
    public Boolean resultReaval;
    public String obs;

    public AvaliacaoDto() {
    }

    public AvaliacaoDto(Integer tipoServico, Integer idServico, Integer idUsuExect, Integer idUsuConf, Boolean resultado, Date dataAvaliacao) {
        this.tipoServico = tipoServico;
        this.idServico = idServico;
        this.idUsuExect = idUsuExect;
        this.idUsuConf = idUsuConf;
        this.resultado = resultado;
        this.dataAvaliacao = dataAvaliacao;
    }

    public AvaliacaoDto(Integer id, Integer tipoServico, Integer idServico, Integer idUsuExect, Integer idUsuConf, Boolean resultado, Date dataAvaliacao, Date dataReavaliacao, Boolean resultReaval, String obs) {
        this.idAvaliacao = id;
        this.tipoServico = tipoServico;
        this.idServico = idServico;
        this.idUsuExect = idUsuExect;
        this.idUsuConf = idUsuConf;
        this.resultado = resultado;
        this.dataAvaliacao = dataAvaliacao;
        this.dataReavaliacao = dataReavaliacao;
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
    public Integer getTipoServico() {
        return tipoServico;
    }

    @Override
    public void setTipoServico(Integer tipoServico) {
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
    public Integer getIdUsuExect() {
        return idUsuExect;
    }

    @Override
    public void setIdUsuExect(Integer idUsuExect) {
        this.idUsuExect = idUsuExect;
    }

    @Override
    public Integer getIdUsuConf() {
        return idUsuConf;
    }

    @Override
    public void setIdUsuConf(Integer idUsuConf) {
        this.idUsuConf = idUsuConf;
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
}
