package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.AvaliacaoDto;
import javax.persistence.*;
import java.util.Date;

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "Avaliacao.dtoMapping", classes = {
                @ConstructorResult(targetClass = AvaliacaoDto.class,
                        columns ={
                                @ColumnResult(name = "ID_AVALIACAO", type = Integer.class),
                                @ColumnResult(name = "TIPO_SERVICO", type = Integer.class),
                                @ColumnResult(name = "ID_SERVICO", type = Integer.class),
                                @ColumnResult(name = "USU_EXECT", type = Integer.class),
                                @ColumnResult(name = "USU_CONF", type = Integer.class),
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
        @NamedNativeQuery(name="Avaliacao.buscarPorId", query = "SELECT * FROM AVALIACAO WHERE ID_AVALIACAO = :ID_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
        @NamedNativeQuery(name="Avaliacao.buscarPorExecutor", query = "SELECT * FROM AVALIACAO WHERE ID_USU_EXECT = :ID_USU_EXECT ORDER BY DATA_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
        @NamedNativeQuery(name="Avaliacao.buscarPorConferente", query = "SELECT * FROM AVALIACAO WHERE ID_USU_CONF = :ID_USU_CONF ORDER BY DATA_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
        @NamedNativeQuery(name="Avaliacao.buscarPorServico", query = "SELECT * FROM AVALIACAO a INNER JOIN SERVICO s ON a.ID_AVALIACAO = s.ID_SERVICO WHERE s.TIPO_SERVICO = :TIPO_SERVICO ORDER BY DATA_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
        @NamedNativeQuery(name="Avaliacao.listarAvaliacoes", query = "SELECT * FROM AVALIACAO ODER BY ID_AVALIACAO", resultSetMapping = "Avaliacao.dtoMapping"),
})
@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvaliacao;

    @Column(name = "TIPO_SERVICO", nullable = false)
    private String tipoServico;

    @Column(name = "ID_SERVICO", nullable = false)
    private Integer idServico;

    @Column(name = "USU_EXECT", nullable = false)
    private String usuExect;

    @Column(name = "USU_CONF", nullable = false)//Deve pegar o usuário que está logado realizando a avaliação
    private String usuConf;

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

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getUsuExect() {
        return usuExect;
    }

    public void setUsuExect(String usuExect) {
        this.usuExect = usuExect;
    }

    public String getUsuConf() {
        return usuConf;
    }

    public void setUsuConf(String usuConf) {
        this.usuConf = usuConf;
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
