import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { AvaliarService } from './service/avaliar.service';
import { ServicosModel } from '../shared/models/servico.model';
import { lastValueFrom } from 'rxjs';
import { AvaliacaoModel } from '../shared/models/avaliacao.model';
import { ParametrosAlvenariaModel } from '../shared/models/parametros-alvenaria.model';
import { ParamAlvenariaService } from '../shared/service/param-alvenaria.service';
import { ServicosService } from '../shared/service/servico.service';

@Component({
  selector: 'app-avaliar',
  templateUrl: './avaliar.component.html',
  styleUrls: ['./avaliar.component.css']
})
export class AvaliarComponent implements OnInit {

  public displayedColumnsSemAval: string[] = ['tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'valorTotal', 'avaliar'];
  public dataSource : ServicosModel[] = [];
  public tabela: number = 0;
  public servicoSelecionado: ServicosModel = new ServicosModel();

  public prumo?: Boolean;
  public nivel?: Boolean;
  public alinhamento?: Boolean;
  public integridade?: Boolean;
  public limpeza?: Boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramAlvenariaService: ParamAlvenariaService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

  ngOnInit(): void {
    this.listarServicosAguardandoAvaliacao();
  }

  avaliarAlvenariaForm = new FormGroup({
    tipoServico: new FormControl('', Validators.required),
    idServico: new FormControl('',Validators.required),
    usuExect: new FormControl('', Validators.required),
    usuConf: new FormControl('', Validators.required),
    resultado: new FormControl(null, Validators.required),
    dataAvaliacao: new FormControl('', Validators.required),
    obs: new FormControl(''),
    idAvaliacao: new FormControl('',Validators.required),
    prumo: new FormControl(null, Validators.required),
    nivel: new FormControl(null, Validators.required),
    alinhamento: new FormControl(null, Validators.required),
    dimensoes: new FormControl('', Validators.required),
    integridade: new FormControl(null, Validators.required),
    limpeza: new FormControl(null, Validators.required),
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

  listarServicosAguardandoAvaliacao() {
    this.servicosService.listarServicosAguardandoAvaliacao().subscribe(servicos => {
      this.dataSource = servicos;
      this.tabela = 1;
    });
  }

  public avaliacao = new AvaliacaoModel();
  public paramAlvenaria = new ParametrosAlvenariaModel();

  async avaliar(id: number){
    this.servicoSelecionado = await lastValueFrom(this.avaliarService.buscarServicoPorId(id));
    this.tabela = 0;
    this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
    this.avaliacao.idServico = this.servicoSelecionado.idServico;
    this.avaliacao.usuExect = this.servicoSelecionado.executor;
    this.avaliacao.usuConf = this.servicoSelecionado.conferente;
    this.paramAlvenaria.dimensoes = this.servicoSelecionado.dimensao;
    this.avaliacao.dataAvaliacao = new Date();
  }
  
  public async avaliarAlvenaria() {
    try {
      if (
        !this.avaliarAlvenariaForm.get('prumo')?.value ||
        !this.avaliarAlvenariaForm.get('nivel')?.value ||
        !this.avaliarAlvenariaForm.get('alinhamento')?.value ||
        !this.avaliarAlvenariaForm.get('integridade')?.value ||
        !this.avaliarAlvenariaForm.get('limpeza')?.value ||
        !this.avaliarAlvenariaForm.get('resultado')?.value
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }
      this.avaliacao.obs = this.avaliarAlvenariaForm.get('obs')?.value ?? "";
      this.paramAlvenaria.obs = this.avaliarAlvenariaForm.get('obs')?.value ?? "";
      this.paramAlvenaria.prumo = this.avaliarAlvenariaForm.get('prumo')?.value ?? false;
      this.paramAlvenaria.nivel = this.avaliarAlvenariaForm.get('nivel')?.value ?? false;
      this.paramAlvenaria.alinhamento = this.avaliarAlvenariaForm.get('alinhamento')?.value ?? false;
      this.paramAlvenaria.integridade = this.avaliarAlvenariaForm.get('integridade')?.value ?? false;
      this.paramAlvenaria.limpeza = this.avaliarAlvenariaForm.get('limpeza')?.value ?? false;
      this.avaliacao.resultado = this.avaliarAlvenariaForm.get('resultado')?.value ?? false;
      await lastValueFrom(this.avaliarService.avaliarAlvenaria(this.avaliacao));
      this.paramAlvenaria.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramAlvenariaService.salvarParametrosAvaliados(this.paramAlvenaria));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, new Date()));
      alert("Serviço Avaliado com Sucesso!");
    } catch{
      alert("Serviço não cadastrado!\nContate o Administrador");
    }
  }

  cancelar() {
    this.router.navigate(['/api/servico-home']);
  }

}
