package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.ServicoDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SERVICO")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "Servico.dtoMapping", classes = {
                @ConstructorResult(targetClass = ServicoDto.class,
                        columns ={
                        @ColumnResult(name = "ID_SERVICO", type = Integer.class),
                        @ColumnResult(name = "TIPO_SERVICO", type = Integer.class),
                        @ColumnResult(name = "VALOR_UNITARIO", type = Double.class),
                        @ColumnResult(name = "DIMENSAO", type = Double.class),
                        @ColumnResult(name = "UNIDADE_MEDIDA", type = String.class),
                        @ColumnResult(name = "CENTRO_DE_CUSTO", type = String.class),
                        @ColumnResult(name = "LOCAL_EXECUCAO", type = String.class),
                        @ColumnResult(name = "EXECUTOR", type = String.class),
                        @ColumnResult(name = "CONFERENTE", type = String.class),
                        @ColumnResult(name = "DATA_INICIO", type = Date.class),
                        @ColumnResult(name = "PREV_TERMINO", type = Date.class),
                        @ColumnResult(name = "DATA_FINAL", type = Date.class),
                        @ColumnResult(name = "VALOR_TOTAL", type = Double.class),
                        @ColumnResult(name = "OBS", type = String.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name="Servico.buscarPorServico", query = "SELECT * FROM SERVICO WHERE TIPO_SERVICO = :TIPO_SERVICO ORDER BY ID_SERVICO", resultSetMapping = "Servico.dtoMapping")
})
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idServico;

    @Column(name = "TIPO_SERVICO", nullable = false)
    private String tipoServico;

    @Column(name = "VALOR_UNITARIO", nullable = false)
    private Double valorUnitario;

    @Column(name = "DIMENSAO", nullable = false)
    private Double dimensao;

    @Column(name = "UNIDADE_MEDIDA", nullable = false)
    private String unidadeMedida;

    @Column(name = "CENTRO_DE_CUSTO", nullable = false)
    private String centroDeCusto;

    @Column(name = "LOCAL_EXECUCAO", nullable = false)
    private String localExecucao;

    //Vem da tabela "Usuario"
    @Column(name = "EXECUTOR", nullable = false)
    private String executor;

    //Vem do usuário logado que fará o cadastro do serviço
    @Column(name = "CONFERENTE", nullable = false)
    private String conferente;

    @Column(name = "DATA_INICIO", nullable = false)
    private Date dataInicio;

    @Column(name = "PREV_TERMINO", nullable = false)
    private Date previsaoTermino;

    @Column(name = "DATA_FINAL")
    private Date dataFinal;

    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Column(name = "OBS")
    private String obs;

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Double getDimensao() {
        return dimensao;
    }

    public void setDimensao(Double dimensao) {
        this.dimensao = dimensao;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(String centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public String getLocalExecucao() {
        return localExecucao;
    }

    public void setLocalExecucao(String localExecucao) {
        this.localExecucao = localExecucao;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getConferente() {
        return conferente;
    }

    public void setConferente(String conferente) {
        this.conferente = conferente;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getPrevisaoTermino() {
        return previsaoTermino;
    }

    public void setPrevisaoTermino(Date previsaoTermino) {
        this.previsaoTermino = previsaoTermino;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal() {
        this.valorTotal = this.getDimensao() * this.getValorUnitario();
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}