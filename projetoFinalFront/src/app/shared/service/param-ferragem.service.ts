import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParametrosFerragemModel } from '../models/parametros-ferragem.model';

@Injectable({
  providedIn: 'root'
})
export class ParamFerragemService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  salvarParametrosAvaliados(paramFerragem: ParametrosFerragemModel): Observable<ParametrosFerragemModel> {
    return this.httpClient.post<ParametrosFerragemModel>("http://localhost:8082/parametros-ferragem/salvarNovosParametros",paramFerragem, this.httpOptions);
  }
}
