import { Component, OnInit } from '@angular/core';
import { DateAdapter } from '@angular/material/core';
import { AvaliarService } from './service/avaliar.service';
import { ServicosModel } from '../shared/models/servico.model';
import { lastValueFrom } from 'rxjs';
import { AvaliacaoModel } from '../shared/models/avaliacao.model';
import { ServicosService } from '../shared/service/servico.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-avaliar',
  templateUrl: './avaliar.component.html',
  styleUrls: ['./avaliar.component.css']
})
export class AvaliarComponent implements OnInit {

  public displayedColumnsSemAval: string[] = ['idServico', 'tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'valorTotal', 'avaliar'];
  public dataSource : ServicosModel[] = [];
  public tabela: number = 0;
  public servicoSelecionado: ServicosModel = new ServicosModel();
  public avaliacao = new AvaliacaoModel();

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private centroService: CentroCustoService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

  ngOnInit(): void {
    this.listarServicosAguardandoAvaliacao();
  }

  public erroCampoVazio = new FormControl('', Validators.required);
    getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }

  listarServicosAguardandoAvaliacao() {
    this.servicosService.listarServicosAguardandoAvaliacao().subscribe(servicos => {
      this.dataSource = servicos;
      this.tabela = 1;
    });
  }

  async avaliar(id: number){
    this.servicoSelecionado = await lastValueFrom(this.avaliarService.buscarServicoPorId(id));
    this.tabela = 0;
    this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
    this.avaliacao.idServico = this.servicoSelecionado.idServico;
    this.avaliacao.usuExect = this.servicoSelecionado.executor;
    this.avaliacao.usuConf = this.servicoSelecionado.conferente;
    this.avaliacao.dataAvaliacao = new Date();
  }

  cancelar() {
    this.router.navigate(['/api/servico-home']);
  }

}
