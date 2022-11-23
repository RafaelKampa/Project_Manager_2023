import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CadastroServicoModel } from 'src/app/cadastro-servico/model/cadastro-servico.model';
import { AvaliacaoAlvenariaModel } from '../model/avaliar.model';

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

  buscarServicoPorId(idServico: number, tipoServico: string) {
    return this.httpClient.get<CadastroServicoModel>("http://localhost:8082/servico/buscarPorId/"+ idServico + "/" + tipoServico, this.httpOptions);
  }

  avaliarAlvenaria(avaliacaoModel: AvaliacaoAlvenariaModel) {
    return this.httpClient.post<AvaliacaoAlvenariaModel>("http://localhost:8082/avaliacao/avaliar", this.httpOptions);
  }

  salvarParametrosAlvenaria(avaliacaoModel: AvaliacaoAlvenariaModel) {
    return this.httpClient.post<AvaliacaoAlvenariaModel>("http://localhost:8082/parametros-alvenaria/salvarNovosParametros", this.httpOptions);
  }

  
}
