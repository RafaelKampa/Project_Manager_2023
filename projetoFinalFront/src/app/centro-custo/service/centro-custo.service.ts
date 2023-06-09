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

  public salvarNovoCentro(centroModel: CentroCustoModel) {
    return this.httpClient.post<CentroCustoModel>("http://localhost:8082/centroDeCusto/salvarNovoCentroDeCusto",centroModel, this.httpOptions);
  }
  public listarCentrosDeCusto(): Observable<CentroCustoModel[]> {
    return this.httpClient.get<CentroCustoModel[]>("http://localhost:8082/centroDeCusto/listarCentrosDeCusto", this.httpOptions);
  }

  public incluirValor(centroDeCusto: string, valorIncremento: number) {
    return this.httpClient.put("http://localhost:8082/centroDeCusto/incluirValor/" + centroDeCusto + "/" + valorIncremento, null, this.httpOptions);
  }

  public buscarPorNome(nomeCentroDeCusto: string): Observable<CentroCustoModel> {
    return this.httpClient.get<CentroCustoModel>("http://localhost:8082/centroDeCusto/buscarPorNome/" + nomeCentroDeCusto, this.httpOptions);
  }

}
