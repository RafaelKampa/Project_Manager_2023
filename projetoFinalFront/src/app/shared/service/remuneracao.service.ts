import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RemuneracaoModel } from '../models/remuneracao.model';

@Injectable({
  providedIn: 'root'
})
export class RemuneracaoService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  salvarNovaRemuneracao(remuneracao: RemuneracaoModel): Observable<RemuneracaoModel> {
    return this.httpClient.post<RemuneracaoModel>("http://localhost:8082/remuneracao/salvarNovaRemuneracao", remuneracao);
  }

  buscarUltimaRemuneracaoUsuario(id_usuario: number) {
    return this.httpClient.get("http://localhost:8082/remuneracao/buscarUltimaRemuneracaoUsuario/" + id_usuario, this.httpOptions);
  }
}
