import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import * as _moment from 'moment';
import {default as _rollupMoment, Moment} from 'moment';
import { CentroCustoModel } from '../shared/models/centro-custo.model';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { lastValueFrom } from 'rxjs';
import { ValorTotalCentroPeriodoModel } from '../shared/models/valor-total-centro-periodo.model';
import { FinanceiroService } from './service/financeiro.service';
import { ProducaoMensalFuncionarioModel } from '../shared/models/producao-mensal-funcionario.model';
import { UsuarioService } from '../usuario/service/usuario.service';
import { UsuarioModel } from '../usuario/model/usuario.model';
import { RemuneracaoService } from '../shared/service/remuneracao.service';

const moment = _rollupMoment || _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/YYYY',
  },
  display: {
    dateInput: 'MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@Component({
  selector: 'app-financeiro',
  templateUrl: './financeiro.component.html',
  styleUrls: ['./financeiro.component.css'],
  providers: [
    {
      provide: DateAdapter,
      useClass: MomentDateAdapter,
      deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]
    },

    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
  encapsulation: ViewEncapsulation.None,
})

export class FinanceiroComponent implements OnInit {

  public date = new FormControl(moment());
  public buscaSelecionada: string = "";
  public periodoSelecionado: number[] = [];
  public centrosDeCusto: CentroCustoModel[] = [];
  public centroDeCustoSelecionado: CentroCustoModel = new CentroCustoModel();
  public funcionarios: UsuarioModel[] = [];
  public funcionarioSelecionado: UsuarioModel = new UsuarioModel();
  public valorTotalCentro: ValorTotalCentroPeriodoModel[] = [];
  public producaoMensalFuncionario: ProducaoMensalFuncionarioModel[] = [];
  public remuneracaoMensal: number = 0;
  public tipoTabela: string = "";
  public colunasCentro: string[] = ['tipoServico', 'valorServico'];
  public colunasFuncionario: string[] = ['centroDeCusto', 'tipoServico', 'localExecucao', 'dataFinal', 'valorServico'];

  constructor(
    private centroCustoServ: CentroCustoService,
    private usuarioServ: UsuarioService,
    private remuneracaoServ: RemuneracaoService,
    private financeiroServ: FinanceiroService
  ) {
  }

  public ngOnInit() {
    this.buscarCentrosDeCusto();
    this.buscarFuncionarios();
    this.periodoSelecionado[0] = new Date().getMonth() + 1; //Adicionado 1 pois o getMonth() inicia em 0
    this.periodoSelecionado[1] = new Date().getFullYear();
  }

  public buscarCentrosDeCusto() {
    this.centroCustoServ.listarCentrosDeCusto().subscribe(centrosDeCusto => { this.centrosDeCusto = centrosDeCusto});
  }

  public buscarFuncionarios() {
    this.usuarioServ.buscarExecutores().subscribe(funcionarios => { this.funcionarios = funcionarios });
  }

  public setMonthAndYear(normalizedMonthAndYear: Moment, datepicker: MatDatepicker<Moment>) {
    const ctrlValue = this.date.value!;
    ctrlValue.month(normalizedMonthAndYear.month());
    ctrlValue.year(normalizedMonthAndYear.year());
    this.date.setValue(ctrlValue);
    this.periodoSelecionado[0] = normalizedMonthAndYear.month() + 1; //normalizedMonthAndYear.month() começa em 0, portanto é necessário adicionar 1
    this.periodoSelecionado[1] = normalizedMonthAndYear.year();
    datepicker.close();
  }

  public async carregarBusca() {
    if (this.buscaSelecionada == "centroDeCusto") {
      this.valorTotalCentro = await lastValueFrom(this.financeiroServ.buscarValorTotalPorCentro(this.centroDeCustoSelecionado.nomeCentroDeCusto, this.periodoSelecionado[0], this.periodoSelecionado[1]));
      if (this.valorTotalCentro.length > 0) {
        const valorTotal: ValorTotalCentroPeriodoModel = new ValorTotalCentroPeriodoModel();
        valorTotal.tipoServico = "Valor total";
        valorTotal.mesPeriodo = this.periodoSelecionado[0];
        valorTotal.anoPeriodo =  this.periodoSelecionado[1];
        valorTotal.valorServico = this.calcularValorTotalCentro();
        this.valorTotalCentro.push(valorTotal);
      }
      this.tipoTabela = this.buscaSelecionada;
    }
    if (this.buscaSelecionada == "funcionario") {
      var remuneracaoControl = await lastValueFrom(this.remuneracaoServ.buscarRemuneracaoPorMes(this.funcionarioSelecionado.idUsuario, this.periodoSelecionado[0], this.periodoSelecionado[1]));
      this.remuneracaoMensal = Number(remuneracaoControl);
      this.financeiroServ.buscarProducaoFuncionario(this.funcionarioSelecionado.nome, this.periodoSelecionado[0], this.periodoSelecionado[1]).subscribe(producao => {
        this.producaoMensalFuncionario = producao;
        var remuneracao: ProducaoMensalFuncionarioModel = new ProducaoMensalFuncionarioModel();
        remuneracao.centroDeCusto = this.funcionarioSelecionado.contratante;
        remuneracao.tipoServico = "Remuneração"
        remuneracao.valorTotal = this.remuneracaoMensal;
        this.producaoMensalFuncionario.push(remuneracao);
        
        var valorTotal: ProducaoMensalFuncionarioModel = new ProducaoMensalFuncionarioModel();
        valorTotal.centroDeCusto = "Valor total";
        valorTotal.valorTotal = this.calcularValorTotalFuncionario();
        this.producaoMensalFuncionario.push(valorTotal);
        this.tipoTabela = this.buscaSelecionada;
      });
    }
  }

  public calcularValorTotalCentro(): number {
    let total = 0;
    for (const item of this.valorTotalCentro) {
      total += item.valorServico;
    }
    return total;
  }

  public calcularValorTotalFuncionario(): number {
    let total = 0;
    for (const item of this.producaoMensalFuncionario) {
      total += item.valorTotal;
    }
    return total;
  }

  public limparDados() {
    this.periodoSelecionado[0] = new Date().getMonth() + 1; //Adicionado 1 pois o getMonth() inicia em 0
    this.periodoSelecionado[1] = new Date().getFullYear();
    this.date = new FormControl(moment());
    this.buscaSelecionada = "";
    this.centroDeCustoSelecionado = new CentroCustoModel();
    this.funcionarioSelecionado = new UsuarioModel();
    this.valorTotalCentro = [];
    this.tipoTabela = "";
  }
}
