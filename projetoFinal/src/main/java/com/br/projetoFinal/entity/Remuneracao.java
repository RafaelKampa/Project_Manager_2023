package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.AvaliacaoDto;
import com.br.projetoFinal.dto.RemuneracaoDto;

import javax.persistence.*;

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "Remuneracao.dtoMapping", classes = {
                @ConstructorResult(targetClass = RemuneracaoDto.class,
                        columns ={
                                @ColumnResult(name = "ID_REMUNERACAO", type = Integer.class),
                                @ColumnResult(name = "ID_USUARIO", type = Integer.class),
                                @ColumnResult(name = "FUNCAO", type = String.class),
                                @ColumnResult(name = "MES_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "ANO_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "VALOR", type = Double.class)
                        }
                )
        })
})
@NamedNativeQueries({
            @NamedNativeQuery(name="Remuneracao.buscarUltimaRemuneracaoUsuario", query = "SELECT VALOR FROM REMUNERACAO WHERE ID_USUARIO = :ID_USUARIO AND (ANO_REFERENCIA, MES_REFERENCIA) = (SELECT ANO_REFERENCIA, MAX(MES_REFERENCIA) FROM REMUNERACAO WHERE ID_USUARIO = :ID_USUARIO AND ANO_REFERENCIA = (SELECT MAX(ANO_REFERENCIA) FROM REMUNERACAO WHERE ID_USUARIO = :ID_USUARIO))", resultSetMapping = "Remuneracao.dtoMapping"),
            @NamedNativeQuery(name="Remuneracao.listarRemuneracoesUsuario", query = "SELECT * FROM REMUNERACAO WHERE ID_USUARIO = :ID_USUARIO", resultSetMapping = "Remuneracao.dtoMapping"),
})

@Entity
@Table(name = "REMUNERACAO")
public class Remuneracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRemuneracao;

    @Column(name = "ID_USUARIO", nullable = false)
    private Integer idUsuario;

    @Column(name = "FUNCAO", nullable = false)
    private String funcao;

    @Column(name = "MES_REFERENCIA", nullable = false)
    private Integer mesReferencia;

    @Column(name = "ANO_REFERENCIA", nullable = false)
    private Integer anoReferencia;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    public Integer getIdRemuneracao() {
        return idRemuneracao;
    }

    public void setIdRemuneracao(Integer idRemuneracao) {
        this.idRemuneracao = idRemuneracao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
