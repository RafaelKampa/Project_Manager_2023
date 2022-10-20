package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.ServicoDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AVALIACAO")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "Avaliacao.dtoMapping", classes = {
                @ConstructorResult(targetClass = ServicoDto.class,
                        columns ={
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "TIPO_SERVICO", type = Integer.class),
                                @ColumnResult(name = "ID_SERVICO", type = Integer.class),
                                @ColumnResult(name = "ID_USU_EXECT", type = Integer.class),
                                @ColumnResult(name = "ID_USU_CONF", type = Integer.class),
                                @ColumnResult(name = "RESULTADO", type = Boolean.class),
                                @ColumnResult(name = "DATA_AVALIACAO", type = Date.class),
                                @ColumnResult(name = "DATA_REAVALIACAO", type = Date.class),
                                @ColumnResult(name = "RESULT_REAVAL", type = Boolean.class),
                                @ColumnResult(name = "OBS", type = String.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name="Avaliacao.buscarPorId", query = "SELECT * FROM AVALIACAO WHERE ID = :ID", resultSetMapping = "Avaliacao.dtoMapping"),
        @NamedNativeQuery(name="Avaliacao.buscarPorExecutor", query = "SELECT * FROM AVALIACAO WHERE ID_USU_EXECT = :ID_USU_EXECT ORDER BY DATA_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
        @NamedNativeQuery(name="Avaliacao.buscarPorConferente", query = "SELECT * FROM AVALIACAO WHERE ID_USU_CONF = :ID_USU_CONF ORDER BY DATA_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
        @NamedNativeQuery(name="Avaliacao.buscarPorServico", query = "SELECT * FROM AVALIACAO WHERE TIPO_SERVICO = :TIPO_SERVICO ORDER BY DATA_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
})
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAvaliacao;

    @Column(name = "TIPO_SERVICO", nullable = false)
    private Integer tipoServico;

    @Column(name = "ID_SERVICO", nullable = false)
    private Integer idServico;

    @Column(name = "ID_USU_EXECT", nullable = false)
    private Integer idUsuExect;

    @Column(name = "ID_USU_CONF", nullable = false)//Deve pegar o usuário que está logado realizando a avaliação
    private Integer idUsuConf;

    @Column(name = "RESULTADO", nullable = false)
    private Boolean resultado;

    @Column(name = "DATA_AVALIACAO", nullable = false)
    private Date dataAvaliacao;

    @Column(name = "DATA_REAVALIACAO")
    private Date dataReavaliacao;

    @Column(name = "RESULT_REAVAL")
    private Boolean resultReaval;

    @Column(name = "OBS")
    private String obs;

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Integer getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(Integer tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdUsuExect() {
        return idUsuExect;
    }

    public void setIdUsuExect(Integer idUsuExect) {
        this.idUsuExect = idUsuExect;
    }

    public Integer getIdUsuConf() {
        return idUsuConf;
    }

    public void setIdUsuConf(Integer idUsuConf) {
        this.idUsuConf = idUsuConf;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Date getDataReavaliacao() {
        return dataReavaliacao;
    }

    public void setDataReavaliacao(Date dataReavaliacao) {
        this.dataReavaliacao = dataReavaliacao;
    }

    public Boolean getResultReaval() {
        return resultReaval;
    }

    public void setResultReaval(Boolean resultReaval) {
        this.resultReaval = resultReaval;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
