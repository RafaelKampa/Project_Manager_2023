import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ValorProducaoModel } from '../models/valor-producao.model';

@Injectable({
  providedIn: 'root'
})
export class ValorProducaoService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  inserirValorProducao(valorProducao: ValorProducaoModel): Observable<ValorProducaoModel> {
    return this.httpClient.post<ValorProducaoModel>("http://localhost:8082/valor-producao/inserirValorProducao", valorProducao, this.httpOptions);
  }

  buscarValorMensal(id_usuario: number, mesReferencia: number, anoReferencia: number) {
    return this.httpClient.get("http://localhost:8082/remuneracao/buscarUltimaRemuneracaoUsuario/" + id_usuario + "/" + mesReferencia + "/" + anoReferencia, this.httpOptions);
  }
}
