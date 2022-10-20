package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.TipoServicoDto;

import javax.persistence.*;

@Entity
@Table(name = "TIPO_SERVICO_TABLE")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "TipoServico.dtoMapping", classes = {
                @ConstructorResult(targetClass = TipoServicoDto.class,
                        columns ={
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "NOME_SERVICO", type = String.class),
                                @ColumnResult(name = "UNIDADE_MEDIDA", type = String.class),
                                @ColumnResult(name = "VALOR_UNITARIO", type = Double.class),
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name="TipoServico.buscarPorId", query = "SELECT * FROM TIPO_SERVICO_TABLE WHERE ID = :ID", resultSetMapping = "TipoServico.dtoMapping"),
        @NamedNativeQuery(name="TipoServico.buscarPorNome", query = "SELECT * FROM TIPO_SERVICO_TABLE WHERE NOME_SERVICO = :NOME_SERVICO ORDER BY ID", resultSetMapping = "TipoServico.dtoMapping")
})
public class TipoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
