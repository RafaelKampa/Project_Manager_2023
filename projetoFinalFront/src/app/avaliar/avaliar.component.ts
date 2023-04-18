import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { ListarServicosService } from '../listar-servicos/service/listar-servicos.service';
import { AvaliacaoAlvenariaModel } from './model/avaliar-alvenaria.model';
import { AvaliarService } from './service/avaliar.service';
import { ServicosModel } from '../shared/models/servico.model';

@Component({
  selector: 'app-avaliar',
  templateUrl: './avaliar.component.html',
  styleUrls: ['./avaliar.component.css']
})
export class AvaliarComponent implements OnInit {

  public displayedColumnsSemAval: string[] = ['tipoServico', 'valorUnitario', 'dimensao', 'unidadeMedida', 'centroDeCusto', 'localExecucao', 'executor', 'conferente', 'dataInicio', 'previsaoTermino', 'valorTotal', 'avaliar'];
  public dataSource : ServicosModel[] = [];
  public tabela: number = 0;
  public servicoSelecionado: any;
  public idServicoSelecionado: number = 0;
  public tipoServicoSelecionado: string = "";
  public valorUnitarioSelecionado: number = 0;
  public dimensaoSelecionada: number = 0;
  public unidadeMedidaSelecionada: string = "";
  public centroDeCustoSelecionado: string = "";
  public localExecucaoSelecionado: string = "";
  public executorSelecionado: string = "";
  public conferenteSelecionado: string = "";
  public dataInicioSelecionada: Date = new Date();
  public previsaoTerminoSelecionado: Date = new Date();
  public dataFinalSelecionada: Date = new Date();
  public valorTotalSelecionado: number = 0;

  public prumo?: Boolean;
  public nivel?: Boolean;
  public alinhamento?: Boolean;
  public integridade?: Boolean;
  public limpeza?: Boolean;
  public resultado?: Boolean;

  avaliarAlvenariaForm = new FormGroup({
    tipoServico: new FormControl('',Validators.required),
    idServico: new FormControl('',Validators.required),
    executor: new FormControl('', Validators.required),
    conferente: new FormControl('',Validators.required),
    resultado: new FormControl('',Validators.required),
    dataAvaliacao: new FormControl('',Validators.required),
    obs: new FormControl(''),
    idAvaliacao: new FormControl('',Validators.required),
    prumo: new FormControl('',Validators.required),
    nivel: new FormControl('',Validators.required),
    alinhamento: new FormControl('',Validators.required),
    integridade: new FormControl('',Validators.required),
    limpeza: new FormControl('',Validators.required),
    valorTotal: new FormControl('',Validators.required),
    dimensoes: new FormControl('',Validators.required),
  });
  
  constructor(private listarServicosService: ListarServicosService,
    private avaliarService: AvaliarService,
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
    this.listarServicosService.listarServicosAguardandoAvaliacao().subscribe(servicos => {
      this.dataSource = servicos;
      this.tabela = 1;
    });
  }

  public avaliacao = new AvaliacaoAlvenariaModel();
  avaliar(id: number, tipoServico: string, executor: string, conferente: string, valorTotal: number, dimensao: number, unidadeMedida: string){

    this.avaliarService.buscarServicoPorId(id,tipoServico).subscribe(servico => {
      this.servicoSelecionado = servico;
      this.tabela = 0;
      this.idServicoSelecionado = id;
      this.tipoServicoSelecionado = tipoServico;
      this.executorSelecionado = executor;
      this.conferenteSelecionado = conferente;
      this.valorTotalSelecionado = valorTotal;
      this.dimensaoSelecionada = dimensao;
      this.unidadeMedidaSelecionada = unidadeMedida;
      this.avaliacao.tipoServico = this.tipoServicoSelecionado;
      this.avaliacao.idServico = this.idServicoSelecionado;
      this.avaliacao.valorUnitario = this.valorUnitarioSelecionado;
      this.avaliacao.dimensao = this.dimensaoSelecionada;
      this.avaliacao.unidadeMedida = this.unidadeMedidaSelecionada;
      this.avaliacao.centroDeCusto = this.centroDeCustoSelecionado;
      this.avaliacao.localExecucao = this.localExecucaoSelecionado;
      this.avaliacao.executor = this.executorSelecionado;
      this.avaliacao.conferente = this.conferenteSelecionado;
      this.avaliacao.dataAvaliacao = new Date();
      this.avaliacao.obs = this.avaliarAlvenariaForm.get('obs')?.value;
      this.avaliacao.prumo = this.avaliarAlvenariaForm.get('prumo')?.value;
      this.avaliacao.nivel = this.avaliarAlvenariaForm.get('nivel')?.value;
      this.avaliacao.alinhamento = this.avaliarAlvenariaForm.get('alinhamento')?.value;
      this.avaliacao.integridade = this.avaliarAlvenariaForm.get('integridade')?.value;
      this.avaliacao.limpeza = this.avaliarAlvenariaForm.get('limpeza')?.value;
      this.avaliacao.resultado = this.avaliarAlvenariaForm.get('resultado')?.value;
      this.avaliacao.valorTotal = this.valorTotalSelecionado;
    })
  }
  

  avaliarAlvenaria() {


      this.avaliarService.avaliarAlvenaria(this.avaliacao).subscribe(aval => {
        alert("Serviço Avaliado com Sucesso!");
        this.router.navigate(['/api/servico-home']);
      }, (err) => {
        alert("Serviço não cadastrado!\nContate o Administrador");
        return;
      });

      this.avaliarService.salvarParametrosAlvenaria(this.avaliacao).subscribe(alvenaria => {
        alert("Serviço Avaliado com Sucesso!");
        this.router.navigate(['/api/servico-home']);
      }, (err) => {
        alert("Serviço não cadastrado!\nContate o Administrador");
        return;
      });
  }

  cancelar() {
    this.router.navigate(['/api/servico-home']);
  }

}
