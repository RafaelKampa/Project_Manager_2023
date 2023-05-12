import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UsuarioModel } from '../model/usuario.model';
import { Observable } from 'rxjs';

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
    return this.httpClient.post<UsuarioModel>("http://localhost:8082/usuario/salvarUsuario",usuarioModel);
  }

  listar() {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/usuario/listarUsuarios", this.httpOptions);
  }
  
  buscarConferentes(): Observable<UsuarioModel[]> {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/usuario/buscarConferentes", this.httpOptions);
  }

  buscarExecutores(): Observable<UsuarioModel[]> {
    return this.httpClient.get<UsuarioModel[]>("http://localhost:8082/usuario/buscarExecutores", this.httpOptions);
  }

  buscarPorNome(nome: string) {
    return this.httpClient.get<UsuarioModel>("http://localhost:8082/usuario/buscarPorNome/{nome}", this.httpOptions);
  }

}