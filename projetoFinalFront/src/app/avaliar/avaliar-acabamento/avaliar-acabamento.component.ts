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
import { ReavaliacaoModel } from '../reavaliar/model/reavaliacao.model';
import { ValorProducaoModel } from '../../shared/models/valor-producao.model';
import { ValorProducaoService } from '../../shared/service/valor-producao.service';
import { UsuarioModel } from '../../usuario/model/usuario.model';
import { UsuarioService } from '../../usuario/service/usuario.service';
import { CentroCustoModel } from 'src/app/shared/models/centro-custo.model';

@Component({
  selector: 'app-avaliar-acabamento',
  templateUrl: './avaliar-acabamento.component.html',
  styleUrls: ['./avaliar-acabamento.component.css']
})
export class AvaliarAcabamentoComponent implements OnInit {

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();

  @Input()
  public indReavaliacao: boolean = false;

  public paramAcabamento = new ParametrosAcabamentoModel();
  public avaliacao = new AvaliacaoModel();
  public reavaliacao = new ReavaliacaoModel(); 
  public dataSource : ServicosModel[] = [];
  public producaoModel: ValorProducaoModel = new ValorProducaoModel();
  public usuarioModel: UsuarioModel = new UsuarioModel();
  public centroDeCustoModel: CentroCustoModel = new CentroCustoModel();
  public conferentesNome: string[] = [];
  public conferenteSelecionado: string = "";
  public prumo?: Boolean;
  public nivel?: Boolean;
  public alinhamento?: Boolean;
  public integridade?: Boolean;
  public limpeza?: Boolean;
  public resultado?: Boolean;
  public avaliarAcabamentoForm: FormGroup = new FormGroup({});

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramAcabamentoService: ParamAcabamentoService,
    private centroService: CentroCustoService,
    private producaoService: ValorProducaoService,
    private usuarioService: UsuarioService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }
  ngOnInit(): void {
    this.buscarConferentes();
    this.avaliarAcabamentoForm = new FormGroup({
      tipoServico: new FormControl('', Validators.required),
      idServico: new FormControl('',Validators.required),
      usuExect: new FormControl('', Validators.required),
      usuConf: new FormControl({value: this.servicoSelecionado.conferente.toString(), disabled: this.indReavaliacao}, Validators.required),
      resultado: new FormControl(null, Validators.required),
      dataAvaliacao: new FormControl('', Validators.required),
      obs: new FormControl(''),
      idAvaliacao: new FormControl('',Validators.required),
      reguamento: new FormControl(null, Validators.required),
      alisamento: new FormControl(null, Validators.required),
      dimensoes: new FormControl('', Validators.required),
      valorTotal: new FormControl('', Validators.required),
    });
  }

  public erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }

  public async buscarConferentes() {
    let conferentes = await lastValueFrom(this.usuarioService.buscarConferentes());
    this.conferentesNome = conferentes.map(usuario => usuario.nome);
  }
  
  public async avaliarAcabamento() {
    try {
      if (
        this.avaliarAcabamentoForm.get('reguamento')?.value == null ||
        this.avaliarAcabamentoForm.get('alisamento')?.value == null ||
        this.avaliarAcabamentoForm.get('resultado')?.value == null
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }

      this.paramAcabamento.obs = this.avaliarAcabamentoForm.get('obs')?.value ?? "";
      this.paramAcabamento.reguamento = this.avaliarAcabamentoForm.get('reguamento')?.value ?? false;
      this.paramAcabamento.alisamento = this.avaliarAcabamentoForm.get('alisamento')?.value ?? false;
      this.paramAcabamento.dimensoes = this.servicoSelecionado.dimensao;

      if (!this.indReavaliacao){ //Avaliação inicial
        this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
        this.avaliacao.idServico = this.servicoSelecionado.idServico;
        this.avaliacao.usuExect = this.servicoSelecionado.executor;
        this.avaliacao.usuConf = this.avaliarAcabamentoForm.get('usuConf')?.value ?? "";
        this.avaliacao.obs = this.avaliarAcabamentoForm.get('obs')?.value ?? "";
        this.avaliacao.dataAvaliacao = new Date();
        this.avaliacao.resultado = this.avaliarAcabamentoForm.get('resultado')?.value ?? false;
  
        await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
        let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
        this.paramAcabamento.idAvaliacao = idAvaliacao;
        await lastValueFrom(this.paramAcabamentoService.salvarParametrosAvaliados(this.paramAcabamento));

        if (this.avaliacao.resultado) {
          await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, true, this.avaliacao.usuConf));
          await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
          this.producaoModel.idServico = this.servicoSelecionado.idServico;
          this.producaoModel.idAvaliacao = idAvaliacao;
          let centroSelecionado = await lastValueFrom(this.centroService.buscarPorNome(this.servicoSelecionado.centroDeCusto));
          this.producaoModel.idCentroDeCusto = centroSelecionado.idCentroDeCusto;
          this.usuarioModel = await lastValueFrom(this.usuarioService.buscarPorNome(this.servicoSelecionado.executor));
          this.producaoModel.idUsuario = this.usuarioModel.idUsuario;
          this.producaoModel.mesReferencia = new Date().getMonth();
          this.producaoModel.anoReferencia = new Date().getFullYear();
          this.producaoModel.valorServico = this.servicoSelecionado.valorTotal;
          await lastValueFrom(this.producaoService.inserirValorProducao(this.producaoModel));

        } else {
          await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, false, this.avaliacao.usuConf));
        }
      
      } else { //Reavaliação
        if (this.servicoSelecionado.idAvaliacao) {
          this.reavaliacao.dataReavaliacao = new Date();
          this.reavaliacao.resultReaval = this.avaliarAcabamentoForm.get('resultado')?.value;
          this.reavaliacao.idAvaliacao = this.servicoSelecionado.idAvaliacao;
          this.reavaliacao.obs = this.avaliarAcabamentoForm.get('obs')?.value ?? "";
          this.reavaliacao.conferente = this.servicoSelecionado.conferente;

          await lastValueFrom(this.avaliarService.reavaliar(this.reavaliacao));
          let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId());
          this.paramAcabamento.idAvaliacao = idAvaliacao;
          await lastValueFrom(this.paramAcabamentoService.salvarParametrosAvaliados(this.paramAcabamento));

          if (this.reavaliacao.resultReaval) {
            await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, true, this.reavaliacao.conferente));
            await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
            this.producaoModel.idServico = this.servicoSelecionado.idServico;
            this.producaoModel.idAvaliacao = idAvaliacao;
            let centroSelecionado = await lastValueFrom(this.centroService.buscarPorNome(this.servicoSelecionado.centroDeCusto));
            this.producaoModel.idCentroDeCusto = centroSelecionado.idCentroDeCusto;
            this.usuarioModel = await lastValueFrom(this.usuarioService.buscarPorNome(this.servicoSelecionado.executor));
            this.producaoModel.idUsuario = this.usuarioModel.idUsuario;
            this.producaoModel.mesReferencia = new Date().getMonth();
            this.producaoModel.anoReferencia = new Date().getFullYear();
            this.producaoModel.valorServico = this.servicoSelecionado.valorTotal;
            await lastValueFrom(this.producaoService.inserirValorProducao(this.producaoModel));

          } else {
            await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, false, this.reavaliacao.conferente));
          }
        } else {
          alert("Serviço não avaliado!\nContate o Administrador");
        }
      }
      
      if (!this.indReavaliacao) {
        alert("Serviço Avaliado com Sucesso!");
      } else {
        alert("Serviço Reavaliado com Sucesso!");
      }
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
    }
  }

}
