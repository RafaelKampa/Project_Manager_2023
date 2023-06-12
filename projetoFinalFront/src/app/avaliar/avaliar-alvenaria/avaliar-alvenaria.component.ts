import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ServicosModel } from '../../shared/models/servico.model';
import { ServicosService } from '../../shared/service/servico.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { CentroCustoService } from '../../centro-custo/service/centro-custo.service';
import { ParametrosAcabamentoModel } from '../../shared/models/parametros-acabamento.model';
import { ParametrosAlvenariaModel } from '../../shared/models/parametros-alvenaria.model';
import { ParametrosCarpintariaModel } from '../../shared/models/parametros-carpintaria.model';
import { ParametrosFerragemModel } from '../../shared/models/parametros-ferragem.model';
import { ParamAlvenariaService } from '../../shared/service/param-alvenaria.service';
import { AvaliarService } from '../service/avaliar.service';
import { AvaliacaoModel } from '../../shared/models/avaliacao.model';
import { ReavaliacaoModel } from '../reavaliar/model/reavaliacao.model';
import { ValorProducaoModel } from '../../shared/models/valor-producao.model';
import { UsuarioModel } from '../../usuario/model/usuario.model';
import { CentroCustoModel } from '../../shared/models/centro-custo.model';
import { ValorProducaoService } from '../../shared/service/valor-producao.service';
import { UsuarioService } from '../../usuario/service/usuario.service';

@Component({
  selector: 'app-avaliar-alvenaria',
  templateUrl: './avaliar-alvenaria.component.html',
  styleUrls: ['./avaliar-alvenaria.component.css']
})
export class AvaliarAlvenariaComponent implements OnInit {

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();

  @Input()
  public indReavaliacao: boolean = false;

  public paramAlvenaria = new ParametrosAlvenariaModel();
  public avaliacao = new AvaliacaoModel();
  public reavaliacao = new ReavaliacaoModel(); 
  public dataSource : ServicosModel[] = [];
  public producaoModel: ValorProducaoModel = new ValorProducaoModel();
  public usuarioModel: UsuarioModel = new UsuarioModel();
  public centroDeCustoModel: CentroCustoModel = new CentroCustoModel();
  public conferentesNome: string[] = [];
  public conferenteSelecionado: string = "";
  public avaliarAlvenariaForm: FormGroup = new FormGroup({});
  public prumo?: Boolean;
  public nivel?: Boolean;
  public alinhamento?: Boolean;
  public integridade?: Boolean;
  public limpeza?: Boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramAlvenariaService: ParamAlvenariaService,
    private centroService: CentroCustoService,
    private producaoService: ValorProducaoService,
    private usuarioService: UsuarioService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

    ngOnInit(): void {
      this.buscarConferentes();
      this.avaliarAlvenariaForm = new FormGroup ({
        tipoServico: new FormControl('', Validators.required),
        idServico: new FormControl('',Validators.required),
        usuExect: new FormControl('', Validators.required),
        usuConf: new FormControl({value: this.servicoSelecionado.conferente.toString(), disabled: this.indReavaliacao}, Validators.required),
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
  
  public async avaliarAlvenaria() {
    try {
      if (
        this.avaliarAlvenariaForm.get('prumo')?.value == null ||
        this.avaliarAlvenariaForm.get('nivel')?.value == null ||
        this.avaliarAlvenariaForm.get('alinhamento')?.value == null ||
        this.avaliarAlvenariaForm.get('integridade')?.value == null ||
        this.avaliarAlvenariaForm.get('limpeza')?.value == null ||
        this.avaliarAlvenariaForm.get('resultado')?.value == null
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }

      this.paramAlvenaria.obs = this.avaliarAlvenariaForm.get('obs')?.value ?? "";
      this.paramAlvenaria.prumo = this.avaliarAlvenariaForm.get('prumo')?.value ?? false;
      this.paramAlvenaria.nivel = this.avaliarAlvenariaForm.get('nivel')?.value ?? false;
      this.paramAlvenaria.alinhamento = this.avaliarAlvenariaForm.get('alinhamento')?.value ?? false;
      this.paramAlvenaria.integridade = this.avaliarAlvenariaForm.get('integridade')?.value ?? false;
      this.paramAlvenaria.limpeza = this.avaliarAlvenariaForm.get('limpeza')?.value ?? false;
      this.paramAlvenaria.dimensoes = this.servicoSelecionado.dimensao;
      
      if (!this.indReavaliacao){ //Avaliação inicial
        this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
        this.avaliacao.idServico = this.servicoSelecionado.idServico;
        this.avaliacao.usuExect = this.servicoSelecionado.executor;
        this.avaliacao.usuConf = this.avaliarAlvenariaForm.get('usuConf')?.value ?? "";
        this.avaliacao.obs = this.avaliarAlvenariaForm.get('obs')?.value ?? "";
        this.avaliacao.dataAvaliacao = new Date();
        this.avaliacao.resultado = this.avaliarAlvenariaForm.get('resultado')?.value ?? false;

        await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
        let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
        this.paramAlvenaria.idAvaliacao = idAvaliacao;
        await lastValueFrom(this.paramAlvenariaService.salvarParametrosAvaliados(this.paramAlvenaria));

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
          this.reavaliacao.resultReaval = this.avaliarAlvenariaForm.get('resultado')?.value ?? false;
          this.reavaliacao.idAvaliacao = this.servicoSelecionado.idAvaliacao;
          this.reavaliacao.obs = this.avaliarAlvenariaForm.get('obs')?.value ?? "";
          this.reavaliacao.conferente = this.servicoSelecionado.conferente;

          await lastValueFrom(this.avaliarService.reavaliar(this.reavaliacao));
          let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
          this.paramAlvenaria.idAvaliacao = idAvaliacao;
          await lastValueFrom(this.paramAlvenariaService.salvarParametrosAvaliados(this.paramAlvenaria));

          if(this.reavaliacao.resultReaval) {
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
