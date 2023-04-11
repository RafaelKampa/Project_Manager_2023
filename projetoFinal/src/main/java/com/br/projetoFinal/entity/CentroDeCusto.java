package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.CentroDeCustoDto;

import javax.persistence.*;

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "CentroDeCusto.dtoMapping", classes = {
                @ConstructorResult(targetClass = CentroDeCustoDto.class,
                        columns ={
                                @ColumnResult(name = "ID_CENTRO_DE_CUSTO", type = Integer.class),
                                @ColumnResult(name = "NOME_CENTRO_DE_CUSTO", type = String.class),
                                @ColumnResult(name = "ENDERECO", type = String.class),
                                @ColumnResult(name = "VALOR_EMPREENDIDO", type = Double.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "CentroDeCusto.listarCentrosDeCusto", query = "SELECT * FROM CENTRO_DE_CUSTO ORDER BY ID_CENTRO_DE_CUSTO ", resultSetMapping = "CentroDeCusto.dtoMapping"),
        @NamedNativeQuery(name = "CentroDeCusto.buscarCentroPorId", query = "SELECT * FROM CENTRO_DE_CUSTO WHERE ID_CENTRO_DE_CUSTO = :ID_CENTRO_DE_CUSTO ", resultSetMapping = "CentroDeCusto.dtoMapping"),
        @NamedNativeQuery(name = "CentroDeCusto.buscarPorNome", query = "SELECT * FROM CENTRO_DE_CUSTO WHERE NOME_CENTRO_DE_CUSTO = :NOME_CENTRO_DE_CUSTO", resultSetMapping = "CentroDeCusto.dtoMapping")
})

@Entity
@Table(name = "CENTRO_DE_CUSTO")
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
