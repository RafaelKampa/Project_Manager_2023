import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { CentroCustoService } from '../../centro-custo/service/centro-custo.service';
import { AvaliacaoModel } from '../../shared/models/avaliacao.model';
import { ParametrosAcabamentoModel } from '../../shared/models/parametros-acabamento.model';
import { ServicosModel } from '../../shared/models/servico.model';
import { ServicosService } from '../../shared/service/servico.service';
import { AvaliarService } from '../service/avaliar.service';
import { ParamAcabamentoService } from '../../shared/service/param-acabamento.service';

@Component({
  selector: 'app-avaliar-acabamento',
  templateUrl: './avaliar-acabamento.component.html',
  styleUrls: ['./avaliar-acabamento.component.css']
})
export class AvaliarAcabamentoComponent {

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();

  public paramAcabamento = new ParametrosAcabamentoModel();
  public avaliacao = new AvaliacaoModel();
  public dataSource : ServicosModel[] = [];
  public prumo?: Boolean;
  public nivel?: Boolean;
  public alinhamento?: Boolean;
  public integridade?: Boolean;
  public limpeza?: Boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramAcabamentoService: ParamAcabamentoService,
    private centroService: CentroCustoService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

  avaliarAcabamentoForm = new FormGroup({
    tipoServico: new FormControl('', Validators.required),
    idServico: new FormControl('',Validators.required),
    usuExect: new FormControl('', Validators.required),
    usuConf: new FormControl('', Validators.required),
    resultado: new FormControl(null, Validators.required),
    dataAvaliacao: new FormControl('', Validators.required),
    obs: new FormControl(''),
    idAvaliacao: new FormControl('',Validators.required),
    reguamento: new FormControl(null, Validators.required),
    alisamento: new FormControl(null, Validators.required),
    dimensoes: new FormControl('', Validators.required),
    valorTotal: new FormControl('', Validators.required),
  });

  public erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }

  
  public async avaliarAcabamento() {
    try {
      if (
        !this.avaliarAcabamentoForm.get('reguamento')?.value ||
        !this.avaliarAcabamentoForm.get('alisamento')?.value ||
        !this.avaliarAcabamentoForm.get('resultado')?.value
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }
      this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
      this.avaliacao.idServico = this.servicoSelecionado.idServico;
      this.avaliacao.usuExect = this.servicoSelecionado.executor;
      this.avaliacao.usuConf = this.servicoSelecionado.conferente;
      this.avaliacao.obs = this.avaliarAcabamentoForm.get('obs')?.value ?? "";
      this.avaliacao.dataAvaliacao = new Date();
      this.avaliacao.resultado = this.avaliarAcabamentoForm.get('resultado')?.value ?? false;

      this.paramAcabamento.obs = this.avaliarAcabamentoForm.get('obs')?.value ?? "";
      this.paramAcabamento.reguamento = this.avaliarAcabamentoForm.get('reguamento')?.value ?? false;
      this.paramAcabamento.alisamento = this.avaliarAcabamentoForm.get('alisamento')?.value ?? false;
      this.paramAcabamento.dimensoes = this.servicoSelecionado.dimensao;
      this.avaliacao.resultado = this.avaliarAcabamentoForm.get('resultado')?.value ?? false;
      await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
      this.paramAcabamento.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramAcabamentoService.salvarParametrosAvaliados(this.paramAcabamento));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico));
      await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
      alert("Serviço Avaliado com Sucesso!");
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
    }
  }

}