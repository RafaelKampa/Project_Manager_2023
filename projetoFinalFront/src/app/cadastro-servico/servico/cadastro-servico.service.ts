import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServicosModel } from '../../shared/models/servico.model';
import { TipoServicoModel } from '../../shared/models/tipo-servico-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CadastroServicoService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  salvarNovoServico(servicoModel: ServicosModel) {
    return this.httpClient.post<ServicosModel>("http://localhost:8082/servico/salvarServico",servicoModel, this.httpOptions);
  }

  listarTipos() {
    return this.httpClient.get<TipoServicoModel[]>("http://localhost:8082/tipo-servico-table/listarTiposServicos", this.httpOptions);
  }

  public concluirServico(id_servico: number): Observable<any> {
    return this.httpClient.put<any>("http://localhost:8082/servico/concluirServico/" + id_servico, this.httpOptions);
  }
}