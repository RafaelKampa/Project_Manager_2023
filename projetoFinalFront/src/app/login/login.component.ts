import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioModel } from '../usuario/model/usuario.model';
import { Autenticacao } from './model/login.model';
import { LoginService } from './service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  usuarioLogado: UsuarioModel = new UsuarioModel();
  listaUsuarios: UsuarioModel[] = [];
  hide: any;
  email: any;
  invalid: any;

  loginForm = new FormGroup({
    login: new FormControl('',Validators.required),
    senha: new FormControl('',Validators.required),
  });


  constructor(private loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {
    
  }

  public logar() {
    if (this.loginForm.valid) {
      let autenticacao = new Autenticacao();
      autenticacao.login = this.loginForm.get('login')?.value;
      autenticacao.senha = this.loginForm.get('senha')?.value;

      this.loginService.login(autenticacao).subscribe(retorno => {
        localStorage.setItem('token', retorno.token);
        this.router.navigate(['/api/servico-home']);
      },
      (err) => {alert("Usuário ou senha incorreto!")});
    }
  }
  
  public esqueciSenha(){
    alert("Problema é seu!");
  }

  cadastrar() {
    this.router.navigate(['/api/usuario']);
  }
}