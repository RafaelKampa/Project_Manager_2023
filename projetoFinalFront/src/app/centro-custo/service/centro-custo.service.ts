import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CentroCustoModel } from '../../shared/models/centro-custo.model';

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

  salvarNovoCentro(centroModel: CentroCustoModel) {
    return this.httpClient.post<CentroCustoModel>("http://localhost:8082/centroDeCusto/salvarNovoCentroDeCusto",centroModel, this.httpOptions);
  }
  listarCentrosDeCusto(): Observable<CentroCustoModel[]> {
    return this.httpClient.get<CentroCustoModel[]>("http://localhost:8082/centroDeCusto/listarCentrosDeCusto", this.httpOptions);
  }

}
