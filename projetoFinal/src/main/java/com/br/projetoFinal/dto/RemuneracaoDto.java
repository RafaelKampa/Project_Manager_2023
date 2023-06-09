package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.Remuneracao;

public class RemuneracaoDto extends Remuneracao {

    private Integer idRemuneracao;
    private Integer idUsuario;
    private String funcao;
    private Integer mesReferencia;
    private Integer anoReferencia;
    private Double valor;

    public RemuneracaoDto() {
    }

    public RemuneracaoDto(Integer idRemuneracao, Integer idUsuario, String funcao, Integer mesReferencia, Integer anoReferencia, Double valor) {
        this.idRemuneracao = idRemuneracao;
        this.idUsuario = idUsuario;
        this.funcao = funcao;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.valor = valor;
    }

    @Override
    public Integer getIdRemuneracao() {
        return idRemuneracao;
    }

    @Override
    public void setIdRemuneracao(Integer idRemuneracao) {
        this.idRemuneracao = idRemuneracao;
    }

    @Override
    public Integer getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String getFuncao() {
        return funcao;
    }

    @Override
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public Integer getMesReferencia() {
        return mesReferencia;
    }

    @Override
    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    @Override
    public Integer getAnoReferencia() {
        return anoReferencia;
    }

    @Override
    public void setAnoReferencia(Integer anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    @Override
    public Double getValor() {
        return valor;
    }

    @Override
    public void setValor(Double valor) {
        this.valor = valor;
    }

}
