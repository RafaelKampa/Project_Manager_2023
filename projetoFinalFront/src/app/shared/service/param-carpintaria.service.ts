import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParametrosCarpintariaModel } from '../models/parametros-carpintaria.model';

@Injectable({
  providedIn: 'root'
})
export class ParamCarpintariaService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  salvarParametrosAvaliados(paramCarpintaria: ParametrosCarpintariaModel): Observable<ParametrosCarpintariaModel> {
    return this.httpClient.post<ParametrosCarpintariaModel>("http://localhost:8082/parametros-carpintaria/salvarNovosParametros", paramCarpintaria, this.httpOptions);
  }
}
