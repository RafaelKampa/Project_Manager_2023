import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListarServicosService } from './service/listar-servicos.service';
import { ServicosModel } from '../shared/models/servico.model';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-listar-servicos',
  templateUrl: './listar-servicos.component.html',
  styleUrls: ['./listar-servicos.component.css']
})
export class ListarServicosComponent implements OnInit {

  public displayedColumns: string[] = ['idServico', 'tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'dataFinal', 'valorTotal'];
  public displayedColumnsSemAval: string[] = ['idServico', 'tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'valorTotal'];
  public cabecalho: number = 0;
  public lista: ServicosModel[] = [];

  constructor(private listarServicosService: ListarServicosService,
    private router: Router) { }

  ngOnInit(): void {
  }

  public async listarTodosServicos () {
    this.cabecalho = 1;
    this.lista = await firstValueFrom(this.listarServicosService.listarServicos());
  }

  public async listarServicosAguardandoAvaliacao () {
    this.cabecalho = 2;
    this.lista = await firstValueFrom(this.listarServicosService.listarServicosAguardandoAvaliacao());
  }

  public async listarServicosAvaliados () {
    this.cabecalho = 1;
    this.lista = await firstValueFrom(this.listarServicosService.listarServicosAvaliados());
  }

  limpar() {
    this.cabecalho = 0;
  }

  voltar() {
    this.router.navigate(['/api/servico-home'])
  }
}
