import { Component, OnInit } from '@angular/core';
import { UsuarioModel } from './model/usuario.model';
import { UsuarioService } from './service/usuario.service';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  cadastroUserForm = new FormGroup({
    username: new FormControl('',Validators.required),
    nome: new FormControl('',Validators.required),
    dataNascimento: new FormControl('',Validators.required),
    cpf: new FormControl('',Validators.required),
    enderecoResidencial: new FormControl('',Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    contratante: new FormControl('',Validators.required),
    dataAdmissao: new FormControl('',Validators.required),
    cargo: new FormControl('',Validators.required),
    remuneracao: new FormControl('',Validators.required),
    telefone: new FormControl(''),
    senha: new FormControl('',Validators.required),
    confirmarSenha: new FormControl('',Validators.required)
  });

  public confirmarSenha = this.cadastroUserForm.get('confirmarSenha')?.value;

  erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.cadastroUserForm.get('email')?.value) {
      return 'Campo obrigatório';
    }
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    }
    return this.cadastroUserForm.get('username')?.hasError('email') ? 'Não é um email válido' : '';
  }

  constructor(private usuarioService: UsuarioService,
    private dateAdapter: DateAdapter<Date>,
    private router: Router) {
      this.dateAdapter.setLocale('pt-BR')
    }

  ngOnInit(): void {
  }

  salvar() {
    let usuario = new UsuarioModel();
      if (this.cadastroUserForm.valid){
      usuario.tipoUsuario = 2; 
      usuario.username = this.cadastroUserForm.get('username')?.value;
      usuario.nome = this.cadastroUserForm.get('nome')?.value;
      usuario.dataNascimento = this.cadastroUserForm.get('dataNascimento')?.value;
      usuario.cpf = this.cadastroUserForm.get('cpf')?.value;
      usuario.enderecoResidencial = this.cadastroUserForm.get('enderecoResidencial')?.value;
      usuario.email = this.cadastroUserForm.get('email')?.value;
      usuario.contratante = this.cadastroUserForm.get('contratante')?.value;
      usuario.dataAdmissao = this.cadastroUserForm.get('dataAdmissao')?.value;
      usuario.cargo = this.cadastroUserForm.get('cargo')?.value;
      usuario.remuneracao = this.cadastroUserForm.get('remuneracao')?.value
      usuario.telefone = this.cadastroUserForm.get('telefone')?.value;
      usuario.senha = this.cadastroUserForm.get('senha')?.value;

      this.usuarioService.salvarUsuario(usuario).subscribe(usuarioRetorno => {
        alert("Usuário Cadastrado! Agora faça o seu login...");
        this.router.navigate(['/api/login']);
      }, err => {
        alert(err);
        return;
      });
    }
  }

  cancelar() {
    this.router.navigate(['/api/login']);
  }

  listar() {
    this.usuarioService.listar().subscribe(usuarios => {
      console.log(usuarios);
    });
  }
}