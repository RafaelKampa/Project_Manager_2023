package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.ParametrosCarpintariaDto;

import javax.persistence.*;

@Entity
@Table(name = "PARAMETROS_CARPINTARIA")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ParametrosCarpintaria.dtoMapping", classes = {
                @ConstructorResult(targetClass = ParametrosCarpintariaDto.class,
                        columns ={
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "TIPO_CARPINTARIA", type = String.class),
                                @ColumnResult(name = "ID_AVALIACAO", type = Integer.class),
                                @ColumnResult(name = "DIMENSOES", type = Double.class),
                                @ColumnResult(name = "NIVEL_OU_PRUMO", type = Boolean.class),
                                @ColumnResult(name = "ESTANQUEIDADE", type = Boolean.class),
                                @ColumnResult(name = "OBS", type = String.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "ParametrosCarpintaria.buscarPorAvaliacao", query = "SELECT * FROM PARAMETROS_CARPINTARIA WHERE ID_AVALIACAO = :ID_AVALIACAO", resultSetMapping = "ParametrosCarpintaria.dtoMapping"),
        @NamedNativeQuery(name = "ParametrosCarpintaria.buscarPorId", query = "SELECT * FROM PARAMETROS_CARPINTARIA WHERE ID = :ID", resultSetMapping = "ParametrosCarpintaria.dtoMapping")
})


public class ParametrosCarpintaria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
