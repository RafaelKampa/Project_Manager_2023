import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UsuarioModel } from '../model/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  salvarUsuario(usuarioModel: UsuarioModel) {
    let tokenCadastro = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0ZSIsImV4cCI6MTY3MDEzNDYxOX0.kIMdcheL2ujx2nefgVZjkAcu62755eEb5A2BnPNxyY7_JF4OWPqIUjUMWNQuao7FMBTV1TMRgd4r3VrJfOkLGw';
    let httpNewUser = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + tokenCadastro
      })
    };
    return this.httpClient.post<UsuarioModel>("http://localhost:8082/usuario/salvarUsuario",usuarioModel, httpNewUser);
  }

  listar() {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/usuario/listarUsuarios", this.httpOptions);
  }
  
  buscarConferentes() {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/usuario/buscarConferentes", this.httpOptions);
  }

  buscarExecutores() {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/usuario/buscarExecutores", this.httpOptions);
  }

  buscarPorNome(NOME: string) {
    return this.httpClient.get<UsuarioModel>("http://localhost:8082/usuario/buscarPorNome/{NOME}", this.httpOptions);
  }

}