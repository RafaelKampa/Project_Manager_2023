import { Component, OnInit } from '@angular/core';
import { UsuarioModel } from './model/usuario.model';
import { UsuarioService } from './service/usuario.service';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  public novoUsuario = new UsuarioModel();
  public confirmarSenha: string = ""

  constructor(private usuarioService: UsuarioService,
    private dateAdapter: DateAdapter<Date>,
    private router: Router) {
      this.dateAdapter.setLocale('en-GB')
    }

  ngOnInit(): void {
  }

  salvar() {
    this.novoUsuario.tipoUsuario = 2;
    let usuario = new UsuarioModel();
    if (this.novoUsuario.login === undefined || this.novoUsuario.login === null){
      alert("O campo 'Login' é obrigatório!");
    }
    if (this.novoUsuario.senha != this.confirmarSenha || this.novoUsuario.senha === undefined || this.novoUsuario.senha === null){
      alert("As senhas não conferem!");
      return;
    }
    if (this.novoUsuario.nome === undefined || this.novoUsuario.nome === null){
      alert("O campo 'Nome' é obrigatório!");
      return;
    }
    if (this.novoUsuario.dataNascimento === undefined || this.novoUsuario.dataNascimento === null){
      alert("O campo 'Data de Nascimento' é obrigatório!");
      return;
    }
    if (this.novoUsuario.enderecoResidencial === undefined || this.novoUsuario.enderecoResidencial === null){
      alert("O campo 'Endereço' é obrigatório!");
      return;
    }
    if (this.novoUsuario.email === undefined || this.novoUsuario.email === null){
      alert("O campo 'E-Mail' é obrigatório!");
      return;
    }
    if (this.novoUsuario.contratante === undefined || this.novoUsuario.contratante === null){
      alert("O campo 'Contratante' é obrigatório! Caso seja autônomo preencher como 'Autônomo'");
      return;
    }
    if (this.novoUsuario.dataAdmissao === undefined || this.novoUsuario.dataAdmissao === null){
      alert("O campo 'Data de Admissão' é obrigatório!");
      return;
    }
    if (this.novoUsuario.cargo === undefined || this.novoUsuario.cargo === null){
        alert("O campo 'Cargo' é obrigatório! Caso seja Autônomo, descrever a função realizada");
        return;
    }
    if (this.novoUsuario.remuneracao === undefined || this.novoUsuario.remuneracao === null){
      alert("O campo 'Remuneração' é obrigatório! Caso seja Autônomo, inserir a remuneração média para a atividade");
      return;
    }
    else {
      usuario = this.novoUsuario; 
      this.usuarioService.salvarUsuario(usuario).subscribe(usuarioRetorno => {
        alert("Usuário Cadastrado! Agora faça o seu login...");
        this.router.navigate(['/api/home']);
      }, err => {
        alert(err);
        return;
      });
    }
  }

  cancelar() {
    this.router.navigate(['/api/home']);
  }

  listar() {
    this.usuarioService.listar().subscribe(usuarios => {
      console.log(usuarios);
    });
  }
}