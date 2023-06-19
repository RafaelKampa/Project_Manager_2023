import { Component, OnInit } from '@angular/core';
import { AvaliacoesModel } from './model/avaliacoes.model';
import { AvaliarService } from '../../avaliar/service/avaliar.service';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-avaliacoes',
  templateUrl: './avaliacoes.component.html',
  styleUrls: ['./avaliacoes.component.css']
})

export class AvaliacoesComponent  implements OnInit {

  public nomeUsuario: string = "";
  public colunas: string[] = ['tipoServico', 'centroDeCusto', 'localExecucao', 'data', 'conferente', 'resultado', 'obs'];
  public avaliacoes : AvaliacoesModel[] = [];

  constructor(
    private avaliacaoServ: AvaliarService) {
  }

  public ngOnInit() {
    const token = localStorage.getItem('token');
    if (token != null) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      this.nomeUsuario = payload.fullName;
    }
    this.listarAvaliacoesPorUsu(this.nomeUsuario);
  }

  public listarAvaliacoesPorUsu(usuExect: string) {
    this.avaliacaoServ.listarAvaliacoesPorUsu(usuExect).subscribe(avaliacoes => {
      this.avaliacoes = avaliacoes;
    });
    // this.avaliacoes = await lastValueFrom(this.avaliacaoServ.listarAvaliacoesPorUsu(usuExect));
  }

  public obterResultado(avaliacao: AvaliacoesModel): string {
    if (avaliacao.resultReaval == null) {
      if (avaliacao.resultado == true) {
        return "Aprovado";
      } else {
        return "Reprovado";
      }
    } else if (avaliacao.resultReaval == true) {
      return "Aprovado";
    } else {
      return "Reprovado";
    }
  }
}
