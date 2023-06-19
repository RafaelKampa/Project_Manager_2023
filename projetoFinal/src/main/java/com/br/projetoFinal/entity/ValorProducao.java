package com.br.projetoFinal.entity;


import com.br.projetoFinal.dto.ValorProducaoDto;

import javax.persistence.*;

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ValorProducao.ValorProducaoDtoMapping", classes = {
                @ConstructorResult(targetClass = ValorProducaoDto.class,
                        columns = {
                                @ColumnResult(name = "ID_VALOR_PRODUCAO", type = Integer.class),
                                @ColumnResult(name = "ID_SERVICO", type = Integer.class),
                                @ColumnResult(name = "ID_AVALIACAO", type = Integer.class),
                                @ColumnResult(name = "ID_CENTRO_DE_CUSTO", type = Integer.class),
                                @ColumnResult(name = "ID_USUARIO", type = Integer.class),
                                @ColumnResult(name = "MES_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "ANO_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "VALOR_SERVICO", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "ValorProducao.valorTotalMapping", classes = {
                @ConstructorResult(targetClass = ValorProducaoDto.class,
                        columns = {
                                @ColumnResult(name = "ID_VALOR_PRODUCAO", type = Integer.class),
                                @ColumnResult(name = "ID_SERVICO", type = Integer.class),
                                @ColumnResult(name = "ID_AVALIACAO", type = Integer.class),
                                @ColumnResult(name = "ID_CENTRO_DE_CUSTO", type = Integer.class),
                                @ColumnResult(name = "ID_USUARIO", type = Integer.class),
                                @ColumnResult(name = "MES_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "ANO_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "VALOR_SERVICO", type = Double.class),
                                @ColumnResult(name = "VALOR_TOTAL", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "ValorProducao.valorMensalMapping", classes = {
                @ConstructorResult(targetClass = ValorProducaoDto.class,
                        columns = {
                                @ColumnResult(name = "ID_USUARIO", type = Integer.class),
                                @ColumnResult(name = "MES_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "ANO_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "VALOR_TOTAL", type = Double.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name="ValorProducao.buscarValorMensal", query = "SELECT SUM(VALOR_SERVICO) AS VALOR_TOTAL FROM VALOR_PRODUCAO WHERE ID_USUARIO = :ID_USUARIO AND MES_REFERENCIA = :MES_REFERENCIA AND ANO_REFERENCIA = :ANO_REFERENCIA", resultSetMapping = "ValorProducao.valorTotalMapping"),
        @NamedNativeQuery(name="ValorProducao.listarProducaoPorUsuario", query = "SELECT ID_USUARIO, MES_REFERENCIA, ANO_REFERENCIA, CAST(SUM(VALOR_SERVICO) AS DECIMAL(10,2)) AS VALOR_TOTAL FROM VALOR_PRODUCAO WHERE ID_USUARIO = :ID_USUARIO GROUP BY ID_USUARIO, MES_REFERENCIA, ANO_REFERENCIA\n", resultSetMapping = "ValorProducao.valorMensalMapping"),
})


@Entity
@Table(name = "VALOR_PRODUCAO")
public class ValorProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idValorProducao;

    @Column(name = "ID_SERVICO", nullable = false)
    private Integer idServico;

    @Column(name = "ID_AVALIACAO", nullable = false)
    private Integer idAvaliacao;

    @Column(name = "ID_CENTRO_DE_CUSTO", nullable = false)
    private Integer idCentroDeCusto;

    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;

    @Column(name = "MES_REFERENCIA", nullable = false)
    private Integer mesReferencia;

    @Column(name = "ANO_REFERENCIA", nullable = false)
    private Integer anoReferencia;

    @Column(name = "VALOR_SERVICO", nullable = false)
    private Double valorServico;

    public Integer getIdValorProducao() {
        return idValorProducao;
    }

    public void setIdValorProducao(Integer idValorProducao) {
        this.idValorProducao = idValorProducao;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getIdCentroDeCusto() {
        return idCentroDeCusto;
    }

    public void setIdCentroDeCusto(Integer idCentroDeCusto) {
        this.idCentroDeCusto = idCentroDeCusto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Integer getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(Integer anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public Double getValorServico() {
        return valorServico;
    }

    public void setValorServico(Double valorServico) {
        this.valorServico = valorServico;
    }
}
