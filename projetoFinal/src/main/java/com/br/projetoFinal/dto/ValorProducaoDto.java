package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.ValorProducao;

import javax.persistence.Column;

public class ValorProducaoDto extends ValorProducao {

    private Integer idValorProducao;
    private Integer idServico;
    private Integer idAvaliacao;
    private Integer idCentroDeCusto;
    private Integer idUsuario;
    private Integer mesReferencia;
    private Integer anoReferencia;
    private Double valorServico;
    private Double valorTotal;

    public ValorProducaoDto() {
    }

    public ValorProducaoDto(Integer idValorProducao, Integer idServico, Integer idAvaliacao, Integer idCentroDeCusto, Integer idUsuario, Integer mesReferencia, Integer anoReferencia, Double valorServico) {
        this.idValorProducao = idValorProducao;
        this.idServico = idServico;
        this.idAvaliacao = idAvaliacao;
        this.idCentroDeCusto = idCentroDeCusto;
        this.idUsuario = idUsuario;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.valorServico = valorServico;
    }

    public ValorProducaoDto(Integer idValorProducao, Integer idServico, Integer idAvaliacao, Integer idCentroDeCusto, Integer idUsuario, Integer mesReferencia, Integer anoReferencia, Double valorServico, Double valorTotal) {
        this.idValorProducao = idValorProducao;
        this.idServico = idServico;
        this.idAvaliacao = idAvaliacao;
        this.idCentroDeCusto = idCentroDeCusto;
        this.idUsuario = idUsuario;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.valorServico = valorServico;
        this.valorTotal = valorTotal;
    }

    @Override
    public Integer getIdValorProducao() {
        return idValorProducao;
    }

    @Override
    public void setIdValorProducao(Integer idValorProducao) {
        this.idValorProducao = idValorProducao;
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
    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    @Override
    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    @Override
    public Integer getIdCentroDeCusto() {
        return idCentroDeCusto;
    }

    @Override
    public void setIdCentroDeCusto(Integer idCentroDeCusto) {
        this.idCentroDeCusto = idCentroDeCusto;
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
    public Double getValorServico() {
        return valorServico;
    }

    @Override
    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
}
