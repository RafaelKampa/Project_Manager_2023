package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.Usuario;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class UsuarioDto extends Usuario {

    private Integer id;
    private String login;
    private String senha;
    private Integer tipoUsuario; //0 = Administrador / 1 = Usuario com privilégios de edição de serviços / 2 = Usuário somente leitura
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String enderecoResidencial;
    private String telefone;
    private String email;
    private String contratante;//Buscar dados da tabela contratante
    private Date dataAdmissao;
    private Date dataDesligamento;
    private String cargo; //Pegar essa informação da tabela "Cargos"
    private double remuneracao; //Pegar essa informação da tabela Cargos}

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id, String login, String senha, Integer tipoUsuario, String nome, Date dataNascimento, String cpf,
                      String enderecoResidencial, String telefone, String email, String contratante, Date dataAdmissao, Date dataDesligamento,
                      String cargo, double remuneracao) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.enderecoResidencial = enderecoResidencial;
        this.telefone = telefone;
        this.email = email;
        this.contratante = contratante;
        this.dataAdmissao = dataAdmissao;
        this.dataDesligamento = dataDesligamento;
        this.cargo = cargo;
        this.remuneracao = remuneracao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
