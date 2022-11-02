package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.TipoServico;

public class TipoServicoDto extends TipoServico {
    public Integer idTipoServico;
    public String nomeServico;
    public String unidadeMedida;
    public Double valorUnitario;

    public TipoServicoDto() {
    }

    public TipoServicoDto(Integer idTipoServico, String nomeServico, String unidadeMedida, Double valorUnitario) {
        this.idTipoServico = idTipoServico;
        this.nomeServico = nomeServico;
        this.unidadeMedida = unidadeMedida;
        this.valorUnitario = valorUnitario;
    }

    @Override
    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    @Override
    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    @Override
    public String getNomeServico() {
        return nomeServico;
    }

    @Override
    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    @Override
    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    @Override
    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public Double getValorUnitario() {
        return valorUnitario;
    }

    @Override
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
