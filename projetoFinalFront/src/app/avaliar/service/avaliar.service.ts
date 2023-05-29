import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ServicosModel } from '../../shared/models/servico.model';
import { Observable } from 'rxjs';
import { AvaliacaoModel } from 'src/app/shared/models/avaliacao.model';

@Injectable({
  providedIn: 'root'
})
export class AvaliarService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  buscarServicoPorId(idServico: number): Observable<ServicosModel> {
    return this.httpClient.get<ServicosModel>("http://localhost:8082/servico/buscarPorId/" + idServico, this.httpOptions);
  }

  avaliar(avaliacaoModel: AvaliacaoModel): Observable<AvaliacaoModel> {
    return this.httpClient.post<AvaliacaoModel>("http://localhost:8082/avaliacao/avaliar", avaliacaoModel, this.httpOptions);
  }

  buscarUltimoId(): Observable<number> {
    return this.httpClient.get<number>("http://localhost:8082/avaliacao/buscarUltimoId", this.httpOptions);
  }
}
