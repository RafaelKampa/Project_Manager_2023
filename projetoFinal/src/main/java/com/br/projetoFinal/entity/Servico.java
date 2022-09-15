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
    private Integer id;

    @Column(name = "TIPOSERVICO", nullable = false, unique = true)
    private String tipoServico;

    @Column(name = "VALORUNITARIO", nullable = false, unique = false)
    private Double valorUnitario;

    @Column(name = "DIMENSAO", nullable = false, unique = false)
    private Double dimensao;

    @Column(name = "LOCALEXECUCAO", nullable = false, unique = false)
    private String localExecucao;

    //Vem da tabela "Usuario"
    @Column(name = "EXECUTOR", nullable = false, unique = false)
    private String executor;

    //Vem do usuário logado que fará o cadastro do serviço
    @Column(name = "CONFERENTE", nullable = false, unique = false)
    private String conferente;

    @Column(name = "DTINICIO", nullable = false, unique = false)
    private Date dataInicio;

    @Column(name = "DATAFINAL", nullable = true, unique = false)
    private Date dataFinal;

    @Column(name = "VALORTOTAL", nullable = true, unique = false)
    private Double valorTotal;

    public Servico(Integer id, String tipoServico, Double valorUnitario, Double dimensao, String localExecucao,
                   String executor, String conferente, Date dataInicio, Date dataFinal, Double valorTotal) {
        this.id = id;
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.valorTotal = valorTotal;
    }

    public Servico(String tipoServico, Double valorUnitario, Double dimensao, String localExecucao, String executor,
                   String conferente, Date dataInicio) {
        this.tipoServico = tipoServico;
        this.valorUnitario = valorUnitario;
        this.dimensao = dimensao;
        this.localExecucao = localExecucao;
        this.executor = executor;
        this.conferente = conferente;
        this.dataInicio = dataInicio;
    }

    public Servico() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}