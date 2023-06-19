import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS } from '@angular/material-moment-adapter';
import { DateAdapter, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import * as _moment from 'moment';
import {default as _rollupMoment, Moment} from 'moment';
import { lastValueFrom } from 'rxjs';
import { CentroCustoService } from '../../centro-custo/service/centro-custo.service';
import { FinanceiroService } from '../../financeiro/service/financeiro.service';
import { AuthguardService } from '../../shared/authguard.service';
import { CentroCustoModel } from '../../shared/models/centro-custo.model';
import { ProducaoMensalFuncionarioModel } from '../../shared/models/producao-mensal-funcionario.model';
import { RemuneracaoModel } from '../../shared/models/remuneracao.model';
import { ValorTotalCentroPeriodoModel } from '../../shared/models/valor-total-centro-periodo.model';
import { RemuneracaoService } from '../../shared/service/remuneracao.service';
import { UsuarioModel } from '../../usuario/model/usuario.model';
import { UsuarioService } from '../../usuario/service/usuario.service';

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
  selector: 'app-producao',
  templateUrl: './producao.component.html',
  styleUrls: ['./producao.component.css'],
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

export class ProducaoComponent implements OnInit {

  public date = new FormControl(moment());
  public periodoSelecionado: number[] = [];
  public producao: ProducaoMensalFuncionarioModel[] = [];
  public remuneracoesUsuario: RemuneracaoModel[] = [];
  public remuneracaoMensal: number = 0;
  public tabela: boolean = false;
  private nomeUsuario: string = "";
  private idUsuario: number = 0;
  public colunasFuncionario: string[] = ['centroDeCusto', 'tipoServico', 'localExecucao', 'dataFinal', 'valorServico'];

  constructor(
    private authService: AuthguardService,
    private usuarioServ: UsuarioService,
    private remuneracaoServ: RemuneracaoService,
    private financeiroServ: FinanceiroService
  ) {
  }

  public ngOnInit() {
    const token = localStorage.getItem('token');
    if (token != null) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      this.nomeUsuario = payload.fullName;
      this.idUsuario = payload.userId;
    }
    this.periodoSelecionado[0] = new Date().getMonth() + 1; //Adicionado 1 pois o getMonth() inicia em 0
    this.periodoSelecionado[1] = new Date().getFullYear();
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
    this.remuneracoesUsuario = await lastValueFrom(this.remuneracaoServ.listarRemuneracoesUsuario(this.idUsuario));
    const remuneracoesEncontradas = this.remuneracoesUsuario.filter(remuneracao =>
      remuneracao.mesReferencia <= this.periodoSelecionado[0] && remuneracao.anoReferencia <= this.periodoSelecionado[1]
    );
    
    if (remuneracoesEncontradas.length > 0) {
      const remuneracaoEncontrada = remuneracoesEncontradas[remuneracoesEncontradas.length - 1];
      this.remuneracaoMensal = remuneracaoEncontrada.valor;
    } else {
      this.remuneracaoMensal = 0;
    }
    
    this.financeiroServ.buscarProducaoFuncionario(this.nomeUsuario, this.periodoSelecionado[0], this.periodoSelecionado[1]).subscribe(producao => {
      this.producao = producao;
      var remuneracao: ProducaoMensalFuncionarioModel = new ProducaoMensalFuncionarioModel();
      remuneracao.tipoServico = "Remuneração"
      remuneracao.valorTotal = this.remuneracaoMensal;
      this.producao.push(remuneracao);
      
      var valorTotal: ProducaoMensalFuncionarioModel = new ProducaoMensalFuncionarioModel();
      valorTotal.centroDeCusto = "Valor total";
      valorTotal.valorTotal = this.calcularValorTotal();
      this.producao.push(valorTotal);
    });
    this.tabela = true;
  }

  public calcularValorTotal(): number {
    let total = 0;
    for (const item of this.producao) {
      total += item.valorTotal;
    }
    return total;
  }

  public limparDados() {
    this.periodoSelecionado[0] = new Date().getMonth() + 1; //Adicionado 1 pois o getMonth() inicia em 0
    this.periodoSelecionado[1] = new Date().getFullYear();
    this.date = new FormControl(moment());
    this.producao = [];
    this.tabela = false;
  }
}
