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
  public valorTotalCentro: ValorTotalCentroPeriodoModel[] = [];
  public tipoTabela: string = "";
  public colunasCentro: string[] = ['tipoServico', 'valorServico'];

  constructor(
    private centroCustoServ: CentroCustoService,
    private financeiroServ: FinanceiroService
  ) {
  }

  public ngOnInit() {
    this.buscarCentrosDeCusto();
    this.periodoSelecionado[0] = new Date().getMonth() + 1; //Adicionado 1 pois o getMonth() inicia em 0
    this.periodoSelecionado[1] = new Date().getFullYear();
  }

  public buscarCentrosDeCusto() {
    this.centroCustoServ.listarCentrosDeCusto().subscribe(centrosDeCusto => { this.centrosDeCusto = centrosDeCusto;});
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
        valorTotal.valorServico = this.calcularValorTotal();
        this.valorTotalCentro.push(valorTotal);
      }
      this.tipoTabela = this.buscaSelecionada;
    }
  }

  public calcularValorTotal(): number {
    let total = 0;
    for (const item of this.valorTotalCentro) {
      total += item.valorServico;
    }
    return total;
  }

  public limpar() {
    this.periodoSelecionado[0] = new Date().getMonth() + 1; //Adicionado 1 pois o getMonth() inicia em 0
    this.periodoSelecionado[1] = new Date().getFullYear();
    this.date = new FormControl(moment());
    this.buscaSelecionada = "";
    this.centroDeCustoSelecionado = new CentroCustoModel();
    this.valorTotalCentro = [];
    this.tipoTabela = "";
  }
}
