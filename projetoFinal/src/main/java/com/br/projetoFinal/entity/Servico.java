package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.ProducaoMensalFuncionarioDto;
import com.br.projetoFinal.dto.ServicoDto;
import com.br.projetoFinal.dto.ValorTotalCentroPeriodoDto;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "Servico.dtoMapping", classes = {
                @ConstructorResult(targetClass = ServicoDto.class,
                        columns ={
                                @ColumnResult(name = "ID_SERVICO", type = Integer.class),
                                @ColumnResult(name = "TIPO_SERVICO", type = String.class),
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
                                @ColumnResult(name = "OBS", type = String.class),
                                @ColumnResult(name = "IND_CONCLUIDO", type = Boolean.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "Servico.dtoMappingAlvenaria", classes = {
                @ConstructorResult(targetClass = ServicoDto.class,
                        columns ={
                                @ColumnResult(name = "ID_SERVICO", type = Integer.class),
                                @ColumnResult(name = "TIPO_SERVICO", type = String.class),
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
                                @ColumnResult(name = "OBS", type = String.class),
                                @ColumnResult(name = "IND_CONCLUIDO", type = Boolean.class),
                                @ColumnResult(name = "ID_AVALIACAO", type = Integer.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "Servico.dtoMappingValorPorcentro", classes = {
                @ConstructorResult(targetClass = ValorTotalCentroPeriodoDto.class,
                        columns ={
                                @ColumnResult(name = "CENTRO_DE_CUSTO", type = String.class),
                                @ColumnResult(name = "MES_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "ANO_REFERENCIA", type = Integer.class),
                                @ColumnResult(name = "TIPO_SERVICO", type = String.class),
                                @ColumnResult(name = "VALOR_TOTAL", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "Servico.dtoMappingProducaoMensalFuncionario", classes = {
                @ConstructorResult(targetClass = ProducaoMensalFuncionarioDto.class,
                        columns ={
                                @ColumnResult(name = "CENTRO_DE_CUSTO", type = String.class),
                                @ColumnResult(name = "TIPO_SERVICO", type = String.class),
                                @ColumnResult(name = "LOCAL_EXECUCAO", type = String.class),
                                @ColumnResult(name = "DATA_FINAL", type = Date.class),
                                @ColumnResult(name = "VALOR_TOTAL", type = Double.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name="Servico.buscarPorTipo", query = "SELECT * FROM SERVICO WHERE TIPO_SERVICO LIKE CONCAT('%', :TIPO_SERVICO, '%') ORDER BY ID_SERVICO", resultSetMapping = "Servico.dtoMapping"),
        @NamedNativeQuery(name="Servico.buscarPorId", query = "SELECT * FROM SERVICO WHERE ID_SERVICO = :ID_SERVICO", resultSetMapping = "Servico.dtoMapping"),
        @NamedNativeQuery(name="Servico.listarTodosServicos", query = "SELECT * FROM SERVICO ORDER BY ID_SERVICO", resultSetMapping = "Servico.dtoMapping"),
        @NamedNativeQuery(name="Servico.servicosAguardandoAval", query = "SELECT * FROM SERVICO WHERE IND_CONCLUIDO IS NULL ORDER BY ID_SERVICO", resultSetMapping = "Servico.dtoMapping"),
        @NamedNativeQuery(name="Servico.servicosAvaliados", query = "SELECT * FROM SERVICO WHERE DATA_FINAL IS NOT NULL ORDER BY ID_SERVICO", resultSetMapping = "Servico.dtoMapping"),
        @NamedNativeQuery(name="Servico.excluirPorId", query = "DELETE FROM SERVICO WHERE ID_SERVICO = :ID_SERVICO", resultSetMapping = "Servico.dtoMapping"),
        @NamedNativeQuery(name="Servico.servicosAguardandoReaval", query = "SELECT S.ID_SERVICO, S.CENTRO_DE_CUSTO, S.CONFERENTE, S.DATA_FINAL, S.DATA_INICIO, S.DIMENSAO, S.EXECUTOR, S.LOCAL_EXECUCAO, S.OBS, S.PREV_TERMINO, S.TIPO_SERVICO, S.UNIDADE_MEDIDA, S.VALOR_TOTAL, S.VALOR_UNITARIO, S.IND_CONCLUIDO, A.ID_AVALIACAO\n" +
                "FROM SERVICO S INNER JOIN AVALIACAO A ON S.ID_SERVICO = A.ID_SERVICO WHERE A.RESULTADO = FALSE AND (A.RESULT_REAVAL IS NULL OR A.RESULT_REAVAL = FALSE)", resultSetMapping = "Servico.dtoMappingAlvenaria"),
        @NamedNativeQuery(name="Servico.buscarValorTotalPorCentro", query = "SELECT CENTRO_DE_CUSTO, MONTH(DATA_FINAL) AS MES_REFERENCIA, YEAR(DATA_FINAL) AS ANO_REFERENCIA, TIPO_SERVICO, CAST(SUM(VALOR_TOTAL) AS DECIMAL(10, 2)) AS VALOR_TOTAL FROM SERVICO WHERE CENTRO_DE_CUSTO = :CENTRO_DE_CUSTO AND MONTH(DATA_FINAL) = :MES_REFERENCIA AND YEAR(DATA_FINAL) = :ANO_REFERENCIA AND IND_CONCLUIDO = TRUE GROUP BY CENTRO_DE_CUSTO, MONTH(DATA_FINAL), YEAR(DATA_FINAL), TIPO_SERVICO", resultSetMapping = "Servico.dtoMappingValorPorcentro"),
        @NamedNativeQuery(name="Servico.buscarProducaoFuncionario", query = "SELECT CENTRO_DE_CUSTO, TIPO_SERVICO, LOCAL_EXECUCAO, DATA_FINAL, VALOR_TOTAL FROM SERVICO WHERE EXECUTOR = :EXECUTOR AND IND_CONCLUIDO = TRUE AND MONTH(DATA_FINAL) = :MES_REFERENCIA AND YEAR(DATA_FINAL) = :ANO_REFERENCIA ORDER BY DATA_FINAL", resultSetMapping = "Servico.dtoMappingProducaoMensalFuncionario"),
})

@Entity
@Table(name = "SERVICO")

public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "IND_CONCLUIDO")
    private Boolean indConcluido;

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

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getIndConcluido() {
        return indConcluido;
    }

    public void setIndConcluido(Boolean indConcluido) {
        this.indConcluido = indConcluido;
    }
}