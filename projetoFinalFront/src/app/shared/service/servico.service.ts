import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServicosModel } from '../../shared/models/servico.model';
import { TipoServicoModel } from '../models/tipo-servico-model';

@Injectable({
  providedIn: 'root'
})
export class ServicosService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  public listarServicos() {
    return this.httpClient.get<ServicosModel[]>("http://localhost:8082/servico/listarServicos", this.httpOptions);
  }

  public listarServicosAguardandoAvaliacao() {
    return this.httpClient.get<ServicosModel[]>("http://localhost:8082/servico/listarAguardandoAvaliacao", this.httpOptions);
  }

  public listarServicosAvaliados() {
    return this.httpClient.get<ServicosModel[]>("http://localhost:8082/servico/listarAvaliados", this.httpOptions);
  }

  public salvarNovoServico(servicoModel: ServicosModel) {
    return this.httpClient.post<ServicosModel>("http://localhost:8082/servico/salvarServico",servicoModel, this.httpOptions);
  }

  public listarTipos() {
    return this.httpClient.get<TipoServicoModel[]>("http://localhost:8082/tipo-servico-table/listarTiposServicos", this.httpOptions);
  }

  public concluirServico(idServico: number) {
    return this.httpClient.put("http://localhost:8082/servico/concluirServico/" + idServico, null, this.httpOptions);
  }
  
}
