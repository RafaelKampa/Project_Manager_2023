package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.TipoServicoDto;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_SERVICO_TABLE")
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoServico;

    @Column(name = "NOME_SERVICO", nullable = false)
    private String nomeServico;

    @Column(name = "UNIDADE_MEDIDA", nullable = false)
    private String unidadeMedida;

    @Column(name = "VALOR_UNITARIO", nullable = false)
    private Double valorUnitario;

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
