import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParametrosAlvenariaModel } from '../models/parametros-alvenaria.model';

@Injectable({
  providedIn: 'root'
})
export class ParamAlvenariaService {

  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'Bearer ' + localStorage.getItem('token')
    })
  };

  salvarParametrosAvaliados(paramAlvenaria: ParametrosAlvenariaModel): Observable<ParametrosAlvenariaModel> {
    return this.httpClient.post<ParametrosAlvenariaModel>("http://localhost:8082/parametros-alvenaria/salvarNovosParametros",paramAlvenaria, this.httpOptions);
  }
}
