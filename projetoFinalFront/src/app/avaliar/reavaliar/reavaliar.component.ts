import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { CentroCustoService } from 'src/app/centro-custo/service/centro-custo.service';
import { AvaliacaoModel } from 'src/app/shared/models/avaliacao.model';
import { ServicosModel } from 'src/app/shared/models/servico.model';
import { ServicosService } from 'src/app/shared/service/servico.service';
import { AvaliarService } from '../service/avaliar.service';

@Component({
  selector: 'app-reavaliar',
  templateUrl: './reavaliar.component.html',
  styleUrls: ['./reavaliar.component.css']
})
export class ReavaliarComponent implements OnInit {

  public displayedColumnsSemAval: string[] = ['idServico', 'tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'valorTotal', 'reavaliar'];
  public dataSource : ServicosModel[] = [];
  public showTabela: boolean = true;
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
    this.listarServicosReprovados();
  }

  public erroCampoVazio = new FormControl('', Validators.required);
    getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }

  listarServicosReprovados() {
    this.servicosService.listarServicosAguardandoReaval().subscribe(servicos => {
      this.dataSource = servicos;
    });
  }

  async reavaliar(id: number, idAvaliacao: number){
    this.servicoSelecionado = await lastValueFrom(this.avaliarService.buscarServicoPorId(id));
    this.servicoSelecionado.idAvaliacao = idAvaliacao;
    this.showTabela = false;
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
