import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ValorTotalCentroPeriodoModel } from '../../shared/models/valor-total-centro-periodo.model';
import { Observable } from 'rxjs';
import { ProducaoMensalFuncionarioModel } from '../../shared/models/producao-mensal-funcionario.model';

@Injectable({
  providedIn: 'root'
})
export class FinanceiroService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };
  
  public buscarValorTotalPorCentro(centroDeCusto: string, mesReferencia: number, anoReferencia: number) {
    return this.httpClient.get<ValorTotalCentroPeriodoModel[]>("http://localhost:8082/servico/buscarValorTotalPorCentro/" + centroDeCusto + "/" + mesReferencia + "/" + anoReferencia, this.httpOptions);
  }

  public buscarProducaoFuncionario(executor: string, mesReferencia: number, anoReferencia: number) {
    return this.httpClient.get<ProducaoMensalFuncionarioModel[]>("http://localhost:8082/servico/buscarProducaoFuncionario/" + executor + "/" + mesReferencia + "/" + anoReferencia, this.httpOptions);
  }
}
