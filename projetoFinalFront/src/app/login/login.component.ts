import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { never } from 'rxjs';
import { UsuarioModel } from '../usuario/model/usuario.model';
import { UsuarioService } from '../usuario/service/usuario.service';
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
    private usuarioService: UsuarioService,
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
        console.log("logou")
        this.router.navigate(['/api/cliente']);
      });
    } else {
      console.log("erro");
    }

  }

  
  public esqueciSenha(){
    alert("Problema é seu!");
  }

  // getErrorMessage() {
  //   if (this.email.hasError('required')) {
  //     return 'You must enter a value';
  //   }

  //   return this.email.hasError('email') ? 'Not a valid email' : '';
  // }

  listar() {
    this.usuarioService.listar().subscribe(usuarios => {
      this.listaUsuarios = usuarios;
      console.log(this.listaUsuarios);
    });
  }

  cadastrar() {
    this.router.navigate(['/api/usuario']);
  }
}