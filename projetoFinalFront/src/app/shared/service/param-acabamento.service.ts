import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParametrosAcabamentoModel } from '../models/parametros-acabamento.model';

@Injectable({
  providedIn: 'root'
})
export class ParamAcabamentoService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  salvarParametrosAvaliados(paramAcabamento: ParametrosAcabamentoModel): Observable<ParametrosAcabamentoModel> {
    return this.httpClient.post<ParametrosAcabamentoModel>("http://localhost:8082/parametros-acabamento/salvarNovosParametros", paramAcabamento, this.httpOptions);
  }
}
