import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { ListarServicosService } from '../listar-servicos/service/listar-servicos.service';
import { AvaliacaoAlvenariaModel } from './model/avaliar-alvenaria.model';
import { AvaliarService } from './service/avaliar.service';
import { ServicosModel } from '../shared/models/servico.model';
import { lastValueFrom } from 'rxjs';

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

  avaliarAlvenariaForm = new FormGroup({
    tipoServico: new FormControl('', Validators.required),
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

  async avaliar(id: number){
    this.servicoSelecionado = await lastValueFrom(this.avaliarService.buscarServicoPorId(id));
    this.tabela = 0;
    this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
    this.avaliacao.idServico = this.servicoSelecionado.idServico;
    this.avaliacao.valorUnitario = this.servicoSelecionado.valorUnitario;
    this.avaliacao.dimensao = this.servicoSelecionado.dimensao;
    this.avaliacao.unidadeMedida = this.servicoSelecionado.unidadeMedida;
    this.avaliacao.centroDeCusto = this.servicoSelecionado.centroDeCusto;
    this.avaliacao.localExecucao = this.servicoSelecionado.localExecucao;
    this.avaliacao.executor = this.servicoSelecionado.executor;
    this.avaliacao.conferente = this.servicoSelecionado.conferente;
    this.avaliacao.dataAvaliacao = new Date();
    this.avaliacao.obs = this.avaliarAlvenariaForm.get('obs')?.value;
    this.avaliacao.prumo = this.avaliarAlvenariaForm.get('prumo')?.value;
    this.avaliacao.nivel = this.avaliarAlvenariaForm.get('nivel')?.value;
    this.avaliacao.alinhamento = this.avaliarAlvenariaForm.get('alinhamento')?.value;
    this.avaliacao.integridade = this.avaliarAlvenariaForm.get('integridade')?.value;
    this.avaliacao.limpeza = this.avaliarAlvenariaForm.get('limpeza')?.value;
    this.avaliacao.resultado = this.avaliarAlvenariaForm.get('resultado')?.value;
    this.avaliacao.valorTotal = this.servicoSelecionado.valorTotal;
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
