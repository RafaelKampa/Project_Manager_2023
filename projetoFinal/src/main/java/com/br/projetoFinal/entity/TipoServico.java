package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.TipoServicoDto;

import javax.persistence.*;

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "TipoServico.dtoMapping", classes = {
                @ConstructorResult(targetClass = TipoServicoDto.class,
                        columns ={
                                @ColumnResult(name = "ID_TIPO_SERVICO", type = Integer.class),
                                @ColumnResult(name = "NOME_SERVICO", type = String.class),
                                @ColumnResult(name = "UNIDADE_MEDIDA", type = String.class),
                                @ColumnResult(name = "VALOR_UNITARIO", type = Double.class),
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name="TipoServico.buscarPorId", query = "SELECT * FROM TIPO_SERVICO_TABLE WHERE ID_TIPO_SERVICO = :ID_TIPO_SERVICO", resultSetMapping = "TipoServico.dtoMapping"),
        @NamedNativeQuery(name="TipoServico.buscarPorNome", query = "SELECT * FROM TIPO_SERVICO_TABLE WHERE NOME_SERVICO LIKE CONCAT('%', :NOME_SERVICO, '%') ORDER BY ID_TIPO_SERVICO", resultSetMapping = "TipoServico.dtoMapping"),
        @NamedNativeQuery(name="TipoServico.listar", query = "SELECT * FROM TIPO_SERVICO_TABLE ORDER BY NOME_SERVICO", resultSetMapping = "TipoServico.dtoMapping"),
        @NamedNativeQuery(name="TipoServico.excluirPorId", query = "DELETE FROM TIPO_SERVICO_TABLE WHERE ID_TIPO_SERVICO = :ID_TIPO_SERVICO", resultSetMapping = "TipoServico.dtoMapping"),
})

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
