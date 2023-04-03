package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.dto.CentroDeCustoDto;

import javax.persistence.*;

@Entity
@Table(name = "CENTRO_DE_CUSTO")
@NamedQueries({
        @NamedQuery(name = "CentroDeCusto.buscarCentroPorId", query = "SELECT c FROM com.br.projetoFinal.entity.CentroDeCusto c WHERE ID_CENTRO_DE_CUSTO = :ID_CENTRO_DE_CUSTO"),
        @NamedQuery(name = "CentroDeCusto.listarCentrosDeCusto", query = "SELECT c FROM com.br.projetoFinal.entity.CentroDeCusto c ORDER BY NOME_CENTRO_DE_CUSTO ")
})

public class CentroDeCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCentroDeCusto;

    @Column(name = "NOME_CENTRO_DE_CUSTO", nullable = false)
    private String nomeCentroDeCusto;

    @Column(name = "ENDERECO", nullable = false)
    private String enderecoCentroDeCusto;

    @Column(name = "VALOR_EMPREENDIDO")
    private Double valorEmpreendido;

    public Integer getIdCentroDeCusto() {
        return idCentroDeCusto;
    }

    public void setIdCentroDeCusto(Integer idCentroDeCusto) {
        this.idCentroDeCusto = idCentroDeCusto;
    }

    public String getNomeCentroDeCusto() {
        return nomeCentroDeCusto;
    }

    public void setNomeCentroDeCusto(String nomeCentroDeCusto) {
        this.nomeCentroDeCusto = nomeCentroDeCusto;
    }

    public String getEnderecoCentroDeCusto() {
        return enderecoCentroDeCusto;
    }

    public void setEnderecoCentroDeCusto(String enderecoCentroDeCusto) {
        this.enderecoCentroDeCusto = enderecoCentroDeCusto;
    }

    public Double getValorEmpreendido() {
        return valorEmpreendido;
    }

    public void setValorEmpreendido(Double valorEmpreendido) {
        this.valorEmpreendido = valorEmpreendido;
    }
}
