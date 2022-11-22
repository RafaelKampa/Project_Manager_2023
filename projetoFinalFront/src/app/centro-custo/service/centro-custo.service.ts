import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CentroCustoModel } from '../model/centro-custo.model';

@Injectable({
  providedIn: 'root'
})
export class CentroCustoService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  listarCentrosDeCusto() {
    return this.httpClient.get<CentroCustoModel[]>("http://localhost:8082/centroDeCusto/listarCentrosDeCusto", this.httpOptions);
  }

}
