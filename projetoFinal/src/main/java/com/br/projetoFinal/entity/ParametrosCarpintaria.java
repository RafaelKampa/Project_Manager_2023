package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;

import javax.persistence.*;

@Entity
@Table(name = "PARAMETROS_CARPINTARIA")
public class ParametrosCarpintaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParametrosCarpintaria;

    @Column(name = "TIPO_CARPINTARIA", nullable = false)
    private String tipoCarpintaria;

    @Column(name = "ID_AVALIACAO", nullable = false)
    private Integer idAvaliacao;

    @Column(name = "DIMENSOES", nullable = false)
    private Double dimensoes;

    @Column(name = "NIVEL_OU_PRUMO", nullable = false)
    private Boolean nivelOuPrumo;

    @Column(name = "ESTANQUEIDADE", nullable = false)
    private Boolean estanqueidade;

    @Column(name = "OBS")
    private String obs;

    public Integer getIdParametrosCarpintaria() {
        return idParametrosCarpintaria;
    }

    public void setIdParametrosCarpintaria(Integer idParametrosCarpintaria) {
        this.idParametrosCarpintaria = idParametrosCarpintaria;
    }

    public String getTipoCarpintaria() {
        return tipoCarpintaria;
    }

    public void setTipoCarpintaria(String tipoCarpintaria) {
        this.tipoCarpintaria = tipoCarpintaria;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Double getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(Double dimensoes) {
        this.dimensoes = dimensoes;
    }

    public Boolean getNivelOuPrumo() {
        return nivelOuPrumo;
    }

    public void setNivelOuPrumo(Boolean nivelOuPrumo) {
        this.nivelOuPrumo = nivelOuPrumo;
    }

    public Boolean getEstanqueidade() {
        return estanqueidade;
    }

    public void setEstanqueidade(Boolean estanqueidade) {
        this.estanqueidade = estanqueidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
