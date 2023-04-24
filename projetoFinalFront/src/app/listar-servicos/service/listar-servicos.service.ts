import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServicosModel } from '../../shared/models/servico.model';

@Injectable({
  providedIn: 'root'
})
export class ListarServicosService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  listarServicos() {
    return this.httpClient.get<ServicosModel[]>("http://localhost:8082/servico/listarServicos", this.httpOptions);
  }

  listarServicosAguardandoAvaliacao() {
    return this.httpClient.get<ServicosModel[]>("http://localhost:8082/servico/listarAguardandoAvaliacao", this.httpOptions);
  }

  listarServicosAvaliados() {
    return this.httpClient.get<ServicosModel[]>("http://localhost:8082/servico/listarAvaliados", this.httpOptions);
  }

  
}
