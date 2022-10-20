package com.br.projetoFinal.entity;

import com.br.projetoFinal.dto.UsuarioDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIO")
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "Usuario.dtoMapping", classes = {
                @ConstructorResult(targetClass = UsuarioDto.class,
                        columns ={
                                @ColumnResult(name = "ID", type = Integer.class),
                                @ColumnResult(name = "LOGIN", type = String.class),
                                @ColumnResult(name = "SENHA", type = String.class),
                                @ColumnResult(name = "TIPO_USUARIO", type = Integer.class),
                                @ColumnResult(name = "NOME", type = String.class),
                                @ColumnResult(name = "DATA_NASCIMENTO", type = Date.class),
                                @ColumnResult(name = "CPF", type = String.class),
                                @ColumnResult(name = "ENDERECO_RESIDENCIAL", type = String.class),
                                @ColumnResult(name = "TELEFONE", type = String.class),
                                @ColumnResult(name = "EMAIL", type = String.class),
                                @ColumnResult(name = "CONTRATANTE", type = String.class),
                                @ColumnResult(name = "DATA_ADMISSAO", type = Date.class),
                                @ColumnResult(name = "DATA_DESLIGAMENTO", type = Date.class),
                                @ColumnResult(name = "CARGO", type = String.class),
                                @ColumnResult(name = "REMUNERACAO", type = Double.class)
                        }
                )
        })
})
@NamedNativeQueries({
        @NamedNativeQuery(name="Usuario.buscarPorId", query = "SELECT * FROM USUARIO WHERE ID = :ID", resultSetMapping = "Usuario.dtoMapping"),
})
public class Usuario {

    //Dados de login
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "SENHA", nullable = false, unique = true)
    private String senha;

    @Column(name = "TIPO_USUARIO", nullable = false)
    private Integer tipoUsuario; //0 = Administrador / 1 = Usuario com privilégios de edição de serviços / 2 = Usuário somente leitura

    //Dados pessoais do usuário
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "ENDERECO_RESIDENCIAL")
    private String enderecoResidencial;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "EMAIL")
    private String email;

    //Dados do contrato do usuario
    @Column(name = "CONTRATANTE", nullable = false)
    private String contratante;//Buscar dados da tabela contratante

    @Column(name = "DATA_ADMISSAO", nullable = false)
    private Date dataAdmissao;

    @Column(name = "DATA_DESLIGAMENTO")
    private Date dataDesligamento;

    @Column(name = "CARGO", nullable = false)
    private String cargo; //Pegar essa informação da tabela "Cargos"

    @Column(name = "REMUNERACAO", nullable = false)
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
