package com.br.projetoFinal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "SERVICO")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idServico;

    @Column(name = "TIPO_SERVICO", nullable = false, unique = false)
    private String tipoServico;

    @Column(name = "VALOR_UNITARIO", nullable = false, unique = false)
    private Double valorUnitario;

    @Column(name = "DIMENSAO", nullable = false, unique = false)
    private Double dimensao;

    @Column(name = "LOCAL_EXECUCAO", nullable = false, unique = false)
    private String localExecucao;

    //Vem da tabela "Usuario"
    @Column(name = "EXECUTOR", nullable = false, unique = false)
    private String executor;

    //Vem do usuário logado que fará o cadastro do serviço
    @Column(name = "CONFERENTE", nullable = false, unique = false)
    private String conferente;

    @Column(name = "DATA_INICIO", nullable = false, unique = false)
    private Date dataInicio;

    @Column(name = "DATA_FINAL", nullable = true, unique = false)
    private Date dataFinal;

    @Column(name = "VALOR_TOTAL", nullable = true, unique = false)
    private Double valorTotal;

    @Column(name = "OBS", nullable = true, unique = false)
    private String obs;

    //Situacao é referente a aprovação ou não do serviço, considerar 0 como não aprovado e 1 como aprovado após a execução
    //por padrão o serviço é cadastrado como 0 e só deve ser alterado após a conferência aprovar a execução do serviço
    //que irá realizar o pagamento do serviço ao funcionário executor
    @Column(name = "SITUACAO", nullable = false, unique = false)
    private int situacao;

    public Servico(Integer idServico, String tipoServico, Double valorUnitario, Double dimensao, String localExecucao, String executor,
                   String conferente, Date dataInicio, Date dataFinal, Double valorTotal, String obs, int situacao) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.valorTotal = valorTotal;
        this.obs = obs;
        this.situacao = situacao;
    }

    public Servico(String tipoServico, Double valorUnitario, Double dimensao, String localExecucao, String executor,
                   String conferente, Date dataInicio, int situacao) {
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
        this.situacao = situacao;
    }

    public Servico() {
        super();
    }

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

    public Double getValor() {
        return valorUnitario;
    }

    public void setValor(Double valor) {
        this.valorUnitario = valor;
    }

    public Double getDimensao() {
        return dimensao;
    }

    public void setDimensao(Double dimensao) {
        this.dimensao = dimensao;
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

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Double getValorTotal() {
        return valorUnitario * dimensao;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}