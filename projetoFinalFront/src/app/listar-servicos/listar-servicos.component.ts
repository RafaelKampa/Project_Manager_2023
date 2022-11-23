import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListarServicosModel } from './model/listar-servicos.model';
import { ListarServicosService } from './service/listar-servicos.service';

@Component({
  selector: 'app-listar-servicos',
  templateUrl: './listar-servicos.component.html',
  styleUrls: ['./listar-servicos.component.css']
})
export class ListarServicosComponent implements OnInit {

  displayedColumns: string[] = ['idServico', 'tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'dataFinal', 'valorTotal'];
  displayedColumnsSemAval: string[] = ['idServico', 'tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'valorTotal'];
  dataSource : ListarServicosModel[] = [];
  cabecalho: number = 0;

  constructor(private listarServicosService: ListarServicosService,
    private router: Router) { }

  ngOnInit(): void {
  }

  listarTodosServicos () {
    this.listarServicosService.listarServicos().subscribe(servicos => {
      this.dataSource = servicos;
      this.cabecalho = 1;
    });
  }

  listarServicosAguardandoAvaliacao () {
    this.listarServicosService.listarServicosAguardandoAvaliacao().subscribe(servicos => {
      this.dataSource = servicos;
      this.cabecalho = 2;
    });
  }

  listarServicosAvaliados () {
    this.listarServicosService.listarServicosAvaliados().subscribe(servicos => {
      this.dataSource = servicos;
      this.cabecalho = 1;
    });
  }

  limpar() {
    this.cabecalho = 0;
  }

  voltar() {
    this.router.navigate(['/api/servico-home'])
  }
}
