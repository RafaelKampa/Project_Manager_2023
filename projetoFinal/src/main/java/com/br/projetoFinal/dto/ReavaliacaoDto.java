package com.br.projetoFinal.dto;

import java.util.Date;

public class ReavaliacaoDto {

    public Integer idAvaliacao;
    public Date dataReavaliacao;
    public Boolean resultReaval;
    public String obs;

    public ReavaliacaoDto(Integer idAvaliacao, Date dataReavaliacao, Boolean resultReaval, String obs) {
        this.idAvaliacao = idAvaliacao;
        this.dataReavaliacao = dataReavaliacao;
        this.resultReaval = resultReaval;
        this.obs = obs;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Date getDataReavaliacao() {
        return dataReavaliacao;
    }

    public void setDataReavaliacao(Date dataReavaliacao) {
        this.dataReavaliacao = dataReavaliacao;
    }

    public Boolean getResultReaval() {
        return resultReaval;
    }

    public void setResultReaval(Boolean resultReaval) {
        this.resultReaval = resultReaval;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
