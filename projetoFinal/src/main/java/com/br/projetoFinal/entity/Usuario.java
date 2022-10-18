package com.br.projetoFinal.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    //Dados de login
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false, unique = true)
    private String senha;

    @Column(name = "tipoUsuario", nullable = false)
    private Integer tipoUsuario; //0 = Administrador / 1 = Usuario com privilégios de edição de serviços / 2 = Usuário somente leitura

    //Dados pessoais do usuário
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "enderecoResidencial")
    private String enderecoResidencial;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    //Dados do contrato do usuario
    @Column(name = "contratante", nullable = false)
    private String contratante;//Buscar dados da tabela contratante

    @Column(name = "dataAdmissao", nullable = false)
    private Date dataAdmissao;

    @Column(name = "dataDesligamento")
    private Date dataDesligamento;

    @Column(name = "cargo", nullable = false)
    private String cargo; //Pegar essa informação da tabela "Cargos"

    @Column(name = "remuneracao", nullable = false)
    private double remuneracao; //Pegar essa informação da tabela Cargos

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEnderecoResidencial() {
        return enderecoResidencial;
    }

    public void setEnderecoResidencial(String enderecoResidencial) {
        this.enderecoResidencial = enderecoResidencial;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContratante() {
        return contratante;
    }

    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(Date dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(double remuneracao) {
        this.remuneracao = remuneracao;
    }
}
