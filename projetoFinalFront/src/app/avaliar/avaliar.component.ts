import { Component, OnInit } from '@angular/core';
import { DateAdapter } from '@angular/material/core';
import { AvaliarService } from './service/avaliar.service';
import { ServicosModel } from '../shared/models/servico.model';
import { lastValueFrom } from 'rxjs';
import { AvaliacaoModel } from '../shared/models/avaliacao.model';
import { ParametrosAlvenariaModel } from '../shared/models/parametros-alvenaria.model';
import { ParamAlvenariaService } from '../shared/service/param-alvenaria.service';
import { ServicosService } from '../shared/service/servico.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { Router } from '@angular/router';
import { ParametrosFerragemModel } from '../shared/models/parametros-ferragem.model';
import { ParamFerragemService } from '../shared/service/param-ferragem.service';
import { ParametrosCarpintariaModel } from '../shared/models/parametros-carpintaria.model';
import { ParamCarpintariaService } from '../shared/service/param-carpintaria.service';
import { ParamAcabamentoService } from '../shared/service/param-acabamento.service';
import { ParametrosAcabamentoModel } from '../shared/models/parametros-acabamento.model';

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

  //Parâmetros de Alvenaria
  public prumo?: Boolean;
  public nivel?: Boolean;
  public alinhamento?: Boolean;
  public integridade?: Boolean;

  //Parâmetros de Ferragem
  public espacamento?: Boolean;
  public distribuicao?: Boolean;

  //Parâmetros de Carpintaria
  public nivelOuPrumo?: boolean;
  public estanqueidade?: boolean;

  //Parâmetros de Acabamento
  public reguamento?: boolean;
  public alisamento?: boolean;

  //Parâmetros comuns
  public limpeza?: Boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramAlvenariaService: ParamAlvenariaService,
    private paramFerragemService: ParamFerragemService,
    private paramCapintariaService: ParamCarpintariaService,
    private paramAcabamentoService: ParamAcabamentoService,
    private centroService: CentroCustoService,
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

  avaliarFerragemForm = new FormGroup({
    tipoServico: new FormControl('', Validators.required),
    idServico: new FormControl('',Validators.required),
    usuExect: new FormControl('', Validators.required),
    usuConf: new FormControl('', Validators.required),
    resultado: new FormControl(null, Validators.required),
    dataAvaliacao: new FormControl('', Validators.required),
    obs: new FormControl(''),
    idAvaliacao: new FormControl('',Validators.required),
    espacamento: new FormControl(null, Validators.required),
    distribuicao: new FormControl(null, Validators.required),
    qtdeAco: new FormControl('', Validators.required),
    valorTotal: new FormControl('', Validators.required),
  });

  avaliarCarpintariaForm = new FormGroup({
    tipoServico: new FormControl('', Validators.required),
    idServico: new FormControl('',Validators.required),
    usuExect: new FormControl('', Validators.required),
    usuConf: new FormControl('', Validators.required),
    resultado: new FormControl(null, Validators.required),
    dataAvaliacao: new FormControl('', Validators.required),
    obs: new FormControl(''),
    tipoCarpintaria: new FormControl('', Validators.required),
    idAvaliacao: new FormControl('',Validators.required),
    nivelOuPrumo: new FormControl(null, Validators.required),
    estanqueidade: new FormControl(null, Validators.required),
    dimensoes: new FormControl('', Validators.required),
    valorTotal: new FormControl('', Validators.required),
  });

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

  listarServicosAguardandoAvaliacao() {
    this.servicosService.listarServicosAguardandoAvaliacao().subscribe(servicos => {
      this.dataSource = servicos;
      this.tabela = 1;
    });
  }

  public avaliacao = new AvaliacaoModel();
  public paramAlvenaria = new ParametrosAlvenariaModel();
  public paramFerragem = new ParametrosFerragemModel();
  public paramCarpintaria = new ParametrosCarpintariaModel();
  public paramAcabamento = new ParametrosAcabamentoModel();

  async avaliar(id: number){
    this.servicoSelecionado = await lastValueFrom(this.avaliarService.buscarServicoPorId(id));
    this.tabela = 0;
    this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
    this.avaliacao.idServico = this.servicoSelecionado.idServico;
    this.avaliacao.usuExect = this.servicoSelecionado.executor;
    this.avaliacao.usuConf = this.servicoSelecionado.conferente;
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
      this.paramAlvenaria.dimensoes = this.servicoSelecionado.dimensao;
      this.avaliacao.resultado = this.avaliarAlvenariaForm.get('resultado')?.value ?? false;
      await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
      this.paramAlvenaria.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramAlvenariaService.salvarParametrosAvaliados(this.paramAlvenaria));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico));
      await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
      alert("Serviço Avaliado com Sucesso!");
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
    }
  }

  public async avaliarFerragem() {
    try {
      if (
        !this.avaliarFerragemForm.get('espacamento')?.value ||
        !this.avaliarFerragemForm.get('distribuicao')?.value ||
        !this.avaliarFerragemForm.get('resultado')?.value
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }
      this.avaliacao.obs = this.avaliarFerragemForm.get('obs')?.value ?? "";
      this.paramFerragem.obs = this.avaliarFerragemForm.get('obs')?.value ?? "";
      this.paramFerragem.distribuicao = this.avaliarFerragemForm.get('distribuicao')?.value ?? false;
      this.paramFerragem.espacamento = this.avaliarFerragemForm.get('espacamento')?.value ?? false;
      this.paramFerragem.qtdeAco = this.servicoSelecionado.dimensao;
      this.avaliacao.resultado = this.avaliarFerragemForm.get('resultado')?.value ?? false;
      await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
      this.paramFerragem.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramFerragemService.salvarParametrosAvaliados(this.paramFerragem));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico));
      await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
      alert("Serviço Avaliado com Sucesso!");
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
    }
  }

  public async avaliarCarpintaria() {
    try {
      if (
        !this.avaliarCarpintariaForm.get('tipoCarpintaria')?.value ||
        !this.avaliarCarpintariaForm.get('nivelOuPrumo')?.value ||
        !this.avaliarCarpintariaForm.get('estanqueidade')?.value ||
        !this.avaliarCarpintariaForm.get('resultado')?.value
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }
      this.avaliacao.obs = this.avaliarCarpintariaForm.get('obs')?.value ?? "";
      this.paramCarpintaria.obs = this.avaliarCarpintariaForm.get('obs')?.value ?? "";
      this.paramCarpintaria.estanqueidade = this.avaliarCarpintariaForm.get('estanqueidade')?.value ?? false;
      this.paramCarpintaria.nivelOuPrumo = this.avaliarCarpintariaForm.get('nivelOuPrumo')?.value ?? false;
      this.paramCarpintaria.dimensoes = this.servicoSelecionado.dimensao;
      this.paramCarpintaria.tipoCarpintaria = this.avaliarCarpintariaForm.get('tipoCarpintaria')?.value ?? "";
      this.avaliacao.resultado = this.avaliarCarpintariaForm.get('resultado')?.value ?? false;
      await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
      this.paramCarpintaria.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramCapintariaService.salvarParametrosAvaliados(this.paramCarpintaria));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico));
      await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
      alert("Serviço Avaliado com Sucesso!");
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
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
      this.avaliacao.obs = this.avaliarAcabamentoForm.get('obs')?.value ?? "";
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

  cancelar() {
    this.router.navigate(['/api/servico-home']);
  }

}
