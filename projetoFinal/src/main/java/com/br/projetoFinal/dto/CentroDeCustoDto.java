package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.CentroDeCusto;

public class CentroDeCustoDto extends CentroDeCusto {

    public Integer idCentroDeCusto;
    public String nomeCentroDeCusto;
    public String enderecoCentroDeCusto;

    public CentroDeCustoDto() {
    }

    public CentroDeCustoDto(Integer idCentroDeCusto, String nomeCentroDeCusto, String enderecoCentroDeCusto) {
        this.idCentroDeCusto = idCentroDeCusto;
        this.nomeCentroDeCusto = nomeCentroDeCusto;
        this.enderecoCentroDeCusto = enderecoCentroDeCusto;
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
    public String getNomeCentroDeCusto() {
        return nomeCentroDeCusto;
    }

    @Override
    public void setNomeCentroDeCusto(String nomeCentroDeCusto) {
        this.nomeCentroDeCusto = nomeCentroDeCusto;
    }

    @Override
    public String getEnderecoCentroDeCusto() {
        return enderecoCentroDeCusto;
    }

    @Override
    public void setEnderecoCentroDeCusto(String enderecoCentroDeCusto) {
        this.enderecoCentroDeCusto = enderecoCentroDeCusto;
    }
}
