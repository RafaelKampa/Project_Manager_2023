import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UsuarioModel } from '../model/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private httpClient: HttpClient) { }

  salvarUsuario(usuarioModel: UsuarioModel) {
    return this.httpClient.post<UsuarioModel>("http://localhost:8082/USUARIO",usuarioModel);
  }

  listar() {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/usuario");
  }

  buscarUltimoId() {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/USUARIO");
  }

}