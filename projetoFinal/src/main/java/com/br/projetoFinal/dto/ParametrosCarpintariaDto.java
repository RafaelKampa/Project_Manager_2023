package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.ParametrosCarpintaria;

public class ParametrosCarpintariaDto extends ParametrosCarpintaria {

    public Integer idParametrosCarpintaria;
    public String tipoCarpintaria;
    public Integer idAvaliacao;
    public Double dimensoes;
    public Boolean nivelOuPrumo;
    public Boolean estanqueidade;
    public String obs;

    public ParametrosCarpintariaDto() {
    }

    public ParametrosCarpintariaDto(Integer idParametrosCarpintaria, String tipoCarpintaria, Integer idAvaliacao,
                                    Double dimensoes, Boolean nivelOuPrumo, Boolean estanqueidade) {
        this.idParametrosCarpintaria = idParametrosCarpintaria;
        this.tipoCarpintaria = tipoCarpintaria;
        this.idAvaliacao = idAvaliacao;
        this.dimensoes = dimensoes;
        this.nivelOuPrumo = nivelOuPrumo;
        this.estanqueidade = estanqueidade;
    }

    public ParametrosCarpintariaDto(Integer idParametrosCarpintaria, String tipoCarpintaria, Integer idAvaliacao,
                                    Double dimensoes, Boolean nivelOuPrumo, Boolean estanqueidade, String obs) {
        this.idParametrosCarpintaria = idParametrosCarpintaria;
        this.tipoCarpintaria = tipoCarpintaria;
        this.idAvaliacao = idAvaliacao;
        this.dimensoes = dimensoes;
        this.nivelOuPrumo = nivelOuPrumo;
        this.estanqueidade = estanqueidade;
        this.obs = obs;
    }

    @Override
    public Integer getIdParametrosCarpintaria() {
        return idParametrosCarpintaria;
    }

    @Override
    public void setIdParametrosCarpintaria(Integer idParametrosCarpintaria) {
        this.idParametrosCarpintaria = idParametrosCarpintaria;
    }

    @Override
    public String getTipoCarpintaria() {
        return tipoCarpintaria;
    }

    @Override
    public void setTipoCarpintaria(String tipoCarpintaria) {
        this.tipoCarpintaria = tipoCarpintaria;
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
    public Double getDimensoes() {
        return dimensoes;
    }

    @Override
    public void setDimensoes(Double dimensoes) {
        this.dimensoes = dimensoes;
    }

    @Override
    public Boolean getNivelOuPrumo() {
        return nivelOuPrumo;
    }

    @Override
    public void setNivelOuPrumo(Boolean nivelOuPrumo) {
        this.nivelOuPrumo = nivelOuPrumo;
    }

    @Override
    public Boolean getEstanqueidade() {
        return estanqueidade;
    }

    @Override
    public void setEstanqueidade(Boolean estanqueidade) {
        this.estanqueidade = estanqueidade;
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
