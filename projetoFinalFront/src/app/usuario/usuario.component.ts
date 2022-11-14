import { Component, OnInit } from '@angular/core';
import { UsuarioModel } from './model/usuario.model';
import { UsuarioService } from './service/usuario.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  public idUsuario: any;
  public login: string = "";
  public senha: string = "";
  public tipoUsuario: number = 2;
  public nome: string = "";
  public dataNascimento: Date = new Date();
  public cpf: string = "";
  public enderecoResidencial: string = "";
  public telefone: string = "";
  public email: string = "";
  public contratante: string = "";
  public dataAdmissao: Date = new Date();
  public dataDesligamento: Date = new Date();
  public cargo: string = "";
  public remuneracao: number = 0;

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
  }

  public async buscarUltimoId() {
    this.idUsuario = await this.usuarioService.buscarUltimoId().toPromise();
  }

  salvar() {
    this.buscarUltimoId();
    let usuario = new UsuarioModel();
    usuario.idUsuario = this.idUsuario.valueOf() + 1;
    usuario.login = this.login;
    usuario.senha = this.senha;
    usuario.tipoUsuario = this.tipoUsuario;
    usuario.nome = this.nome;
    usuario.dataNascimento = this.dataNascimento;
    usuario.cpf = this.cpf;
    usuario.enderecoResidencial = this.enderecoResidencial;
    usuario.telefone = this.telefone;
    usuario.email = this.email;
    usuario.contratante = this.contratante;
    usuario.dataAdmissao = this.dataAdmissao;
    usuario.dataDesligamento = this.dataDesligamento;
    usuario.cargo = this.cargo;
    usuario.remuneracao = this.remuneracao;

    this.usuarioService.salvarUsuario(usuario).subscribe(usuarioRetorno => {
      alert("Usuário Cadastrado!")
      console.log('funcionou');
    }, err => {
      console.log(err);
    });
  }

  cancelar() {
    console.log('idUsuario ' + this.idUsuario);
    console.log('nome ' + this.nome);
    console.log('login ' + this.login);
    console.log('senha ' + this.senha);

    let usuario = new UsuarioModel();
    usuario.idUsuario = this.idUsuario;
    usuario.nome = this.nome;
    usuario.login = this.login;
    usuario.senha = this.senha;

    this.usuarioService.salvarUsuario(usuario).subscribe(usuarioRetorno => {
      console.log('funcionou');
    }, err => {
      console.log(err);
    });
  }

  listar() {
    this.usuarioService.listar().subscribe(usuarios => {
      console.log(usuarios);
    });
  }
}