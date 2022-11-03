package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.ParametrosAcabamentoDto;

import javax.persistence.*;

@Entity
@Table(name = "PARAMETROS_ACABAMENTO")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ParametrosAcabamento.dtoMapping", classes = {
                @ConstructorResult(targetClass = ParametrosAcabamentoDto.class,
                        columns ={
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "ID_AVALIACAO", type = Integer.class),
                                @ColumnResult(name = "DIMENSOES", type = Double.class),
                                @ColumnResult(name = "REGUAMENTO", type = Boolean.class),
                                @ColumnResult(name = "ALISAMENTO", type = Boolean.class),
                                @ColumnResult(name = "OBS", type = String.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "ParametrosAcabamento.buscarPorAvaliacao", query = "SELECT * FROM PARAMETROS_ACABAMENTO WHERE ID_AVALIACAO = :ID_AVALIACAO", resultSetMapping = "ParametrosAcabamento.dtoMapping"),
        @NamedNativeQuery(name = "ParametrosAcabamento.buscarPorId", query = "SELECT * FROM PARAMETROS_ACABAMENTO WHERE ID = :ID", resultSetMapping = "ParametrosAcabamento.dtoMapping")
})
public class ParametrosAcabamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idParametrosAcabamento;

    @Column(name = "ID_AVALIACAO", nullable = false)
    private Integer idAvaliacao;

    @Column(name = "DIMENSOES", nullable = false)
    private Double dimensoes;

    @Column(name = "REGUAMENTO", nullable = false)
    private Boolean reguamento;

    @Column(name = "ALISAMENTO", nullable = false)
    private Boolean alisamento;

    @Column(name = "OBS")
    private String obs;

    public Integer getIdParametrosAcabamento() {
        return idParametrosAcabamento;
    }

    public void setIdParametrosAcabamento(Integer idParametrosAcabamento) {
        this.idParametrosAcabamento = idParametrosAcabamento;
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

    public Boolean getReguamento() {
        return reguamento;
    }

    public void setReguamento(Boolean reguamento) {
        this.reguamento = reguamento;
    }

    public Boolean getAlisamento() {
        return alisamento;
    }

    public void setAlisamento(Boolean alisamento) {
        this.alisamento = alisamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
