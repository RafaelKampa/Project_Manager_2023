package com.br.projetoFinal.dto;

import com.br.projetoFinal.entity.Usuario;

import java.util.Date;

public class UsuarioDto extends Usuario {

    public Integer idUsuario;
    public String username;
    public String senha;
    public Integer tipoUsuario; //0 = Administrador / 1 = Usuario com privilégios de edição de serviços / 2 = Usuário somente leitura
    public String nome;
    public Date dataNascimento;
    public String cpf;
    public String enderecoResidencial;
    public String telefone;
    public String email;
    public String contratante;//Buscar dados da tabela contratante
    public Date dataAdmissao;
    public Date dataDesligamento;
    public String cargo; //Pegar essa informação da tabela "Cargos"
    public double remuneracao; //Pegar essa informação da tabela Cargos}

    public UsuarioDto() {
    }

    public UsuarioDto(Integer idUsuario, String username, String senha, Integer tipoUsuario, String nome, Date dataNascimento, String cpf,
                      String enderecoResidencial, String telefone, String email, String contratante, Date dataAdmissao, Date dataDesligamento,
                      String cargo, double remuneracao) {
        this.idUsuario = idUsuario;
        this.username = username;
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

    @Override
    public Integer getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Date getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getEnderecoResidencial() {
        return enderecoResidencial;
    }

    @Override
    public void setEnderecoResidencial(String enderecoResidencial) {
        this.enderecoResidencial = enderecoResidencial;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    @Override
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getContratante() {
        return contratante;
    }

    @Override
    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    @Override
    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    @Override
    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    @Override
    public Date getDataDesligamento() {
        return dataDesligamento;
    }

    @Override
    public void setDataDesligamento(Date dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    @Override
    public String getCargo() {
        return cargo;
    }

    @Override
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public double getRemuneracao() {
        return remuneracao;
    }

    @Override
    public void setRemuneracao(double remuneracao) {
        this.remuneracao = remuneracao;
    }
}
