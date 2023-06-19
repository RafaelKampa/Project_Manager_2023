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
  hide: boolean = true;
  email: any;
  invalid: any;

  loginForm = new FormGroup({
    login: new FormControl('',Validators.required),
    senha: new FormControl('',Validators.required),
  });


  constructor(private loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {
    localStorage.clear();
  }

  public logar() {
    if (this.loginForm.valid) {
      let autenticacao = new Autenticacao();
      autenticacao.login = this.loginForm.get('login')?.value;
      autenticacao.senha = this.loginForm.get('senha')?.value;
  
      this.loginService.login(autenticacao).subscribe(retorno => {
        localStorage.setItem('token', retorno.token);
        alert("Usuário autenticado!\nRedirecionando...");
  
        const payload = JSON.parse(atob(retorno.token.split('.')[1]));
        const authority = payload.authorities[0]; // Assume a primeira autoridade como a principal
  
        if (authority === "1") {
          this.router.navigate(['/api/servico-home']);
        } else if (authority === "2") {
          this.router.navigate(['/api/dashboard-usuario']);
        } else {
          // Redirecionamento padrão para algum lugar caso a autoridade não seja "1" ou "2"
          this.router.navigate(['/api/home']);
        }
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