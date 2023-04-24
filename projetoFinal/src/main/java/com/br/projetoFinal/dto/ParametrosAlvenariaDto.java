package com.br.projetoFinal.dto;


import com.br.projetoFinal.entity.ParametrosAlvenaria;

public class ParametrosAlvenariaDto extends ParametrosAlvenaria {
    public Integer idParametrosAlvenaria;
    public Integer idAvaliacao;
    public Boolean prumo;
    public Boolean nivel;
    public Boolean alinhamento;
    public Double dimensoes;
    public Boolean integridade;
    public Boolean limpeza;
    public String obs;

    public ParametrosAlvenariaDto() {
    }

    public ParametrosAlvenariaDto(Integer idParametrosAlvenaria, Integer idAvaliacao, Boolean prumo, Boolean nivel, Boolean alinhamento,
                                  Double dimensoes, Boolean integridade, Boolean limpeza, String obs) {
        this.idParametrosAlvenaria = idParametrosAlvenaria;
        this.idAvaliacao = idAvaliacao;
        this.prumo = prumo;
        this.nivel = nivel;
        this.alinhamento = alinhamento;
        this.dimensoes = dimensoes;
        this.integridade = integridade;
        this.limpeza = limpeza;
        this.obs = obs;
    }

    public ParametrosAlvenariaDto(Integer idParametrosAlvenaria, Integer idAvaliacao, Boolean prumo, Boolean nivel, Boolean alinhamento,
                                  Double dimensoes, Boolean integridade, Boolean limpeza) {
        this.idParametrosAlvenaria = idParametrosAlvenaria;
        this.idAvaliacao = idAvaliacao;
        this.prumo = prumo;
        this.nivel = nivel;
        this.alinhamento = alinhamento;
        this.dimensoes = dimensoes;
        this.integridade = integridade;
        this.limpeza = limpeza;
    }

    @Override
    public Integer getIdParametrosAlvenaria() {
        return idParametrosAlvenaria;
    }

    @Override
    public void setIdParametrosAlvenaria(Integer idParametrosAlvenaria) {
        this.idParametrosAlvenaria = idParametrosAlvenaria;
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
    public Boolean getPrumo() {
        return prumo;
    }

    @Override
    public void setPrumo(Boolean prumo) {
        this.prumo = prumo;
    }

    @Override
    public Boolean getNivel() {
        return nivel;
    }

    @Override
    public void setNivel(Boolean nivel) {
        this.nivel = nivel;
    }

    @Override
    public Boolean getAlinhamento() {
        return alinhamento;
    }

    @Override
    public void setAlinhamento(Boolean alinhamento) {
        this.alinhamento = alinhamento;
    }

    @Override
    public Double getDimensoes() {
        return dimensoes;
    }

    @Override
    public void setDimensoes(Double dimensoes) {
        this.dimensoes = dimensoes;
    }

    @Override
    public Boolean getIntegridade() {
        return integridade;
    }

    @Override
    public void setIntegridade(Boolean integridade) {
        this.integridade = integridade;
    }

    @Override
    public Boolean getLimpeza() {
        return limpeza;
    }

    @Override
    public void setLimpeza(Boolean limpeza) {
        this.limpeza = limpeza;
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
