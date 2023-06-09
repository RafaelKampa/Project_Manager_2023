import { Component, OnInit } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { UsuarioModel } from './model/usuario.model';
import { UsuarioService } from './service/usuario.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RemuneracaoService } from '../shared/service/remuneracao.service';
import { RemuneracaoModel } from '../shared/models/remuneracao.model';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})

export class UsuarioComponent implements OnInit {

  public usuario: UsuarioModel = new UsuarioModel();
  public remuneracaoModel: RemuneracaoModel = new RemuneracaoModel();

  constructor(private usuarioService: UsuarioService,
    private remuneracaoService: RemuneracaoService,
    private router: Router) {
    }

  ngOnInit(): void {
  }

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
    senha: new FormControl('',[Validators.required, Validators.min(4)]),
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

  public async salvar() {
    try {
        this.usuario.tipoUsuario = 2; 
        this.usuario.username = this.cadastroUserForm.get('username')?.value ?? "";
        this.usuario.nome = this.cadastroUserForm.get('nome')?.value ?? "";
        const dataNascimento = this.cadastroUserForm.get('dataNascimento')?.value;
        if (dataNascimento) {
        this.usuario.dataNascimento = new Date(dataNascimento);
        }
        this.usuario.cpf = this.cadastroUserForm.get('cpf')?.value ?? "";
        this.usuario.enderecoResidencial = this.cadastroUserForm.get('enderecoResidencial')?.value ?? "";
        this.usuario.email = this.cadastroUserForm.get('email')?.value ?? "";
        this.usuario.contratante = this.cadastroUserForm.get('contratante')?.value ?? "";
        const dataAdmissao = this.cadastroUserForm.get('dataAdmissao')?.value;
        if (dataAdmissao) {
        this.usuario.dataAdmissao = new Date(dataAdmissao);
        }
        this.usuario.cargo = this.cadastroUserForm.get('cargo')?.value ?? "";
        const remuneracao = this.cadastroUserForm.get('remuneracao')?.value;
        if (remuneracao) {
          this.usuario.remuneracao = parseFloat(remuneracao);
        }
        this.usuario.telefone = this.cadastroUserForm.get('telefone')?.value ?? "";
        this.usuario.senha = this.cadastroUserForm.get('senha')?.value ?? "";
        await lastValueFrom(this.usuarioService.salvarUsuario(this.usuario));
        this.remuneracaoModel.idUsuario = +await lastValueFrom(this.usuarioService.buscarUltimoId())
        console.log(this.remuneracaoModel.idUsuario);
        this.remuneracaoModel.funcao = this.usuario.cargo;
        this.remuneracaoModel.mesReferencia = new Date().getMonth();
        this.remuneracaoModel.anoReferencia = new Date().getFullYear();
        this.remuneracaoModel.valor = this.usuario.remuneracao;
        await lastValueFrom(this.remuneracaoService.salvarNovaRemuneracao(this.remuneracaoModel))
        alert("Usuário cadastrado com sucesso! \n Agora faça o login.")
        this.router.navigate(['/api/home']);
    } catch {
      alert("Usuário não cadastrado! \n Contate o Administrador");
    }
  }

  public cancelar() {
    this.router.navigate(['/api/login']);
  }

  public listar() {
    this.usuarioService.listar().subscribe(usuarios => {
      console.log(usuarios);
    });
  }
}