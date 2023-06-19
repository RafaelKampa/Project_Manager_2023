import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { CentroCustoService } from '../../centro-custo/service/centro-custo.service';
import { AvaliacaoModel } from '../../shared/models/avaliacao.model';
import { ServicosModel } from '../../shared/models/servico.model';
import { ParamFerragemService } from '../../shared/service/param-ferragem.service';
import { ServicosService } from '../../shared/service/servico.service';
import { AvaliarService } from '../service/avaliar.service';
import { ParametrosFerragemModel } from '../../shared/models/parametros-ferragem.model';
import { ReavaliacaoModel } from '../reavaliar/model/reavaliacao.model';
import { ValorProducaoModel } from '../../shared/models/valor-producao.model';
import { ValorProducaoService } from '../../shared/service/valor-producao.service';
import { UsuarioModel } from '../../usuario/model/usuario.model';
import { UsuarioService } from '../../usuario/service/usuario.service';

@Component({
  selector: 'app-avaliar-ferragem',
  templateUrl: './avaliar-ferragem.component.html',
  styleUrls: ['./avaliar-ferragem.component.css']
})
export class AvaliarFerragemComponent implements OnInit{

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();

  @Input()
  public indReavaliacao: boolean = false;

  public paramFerragem = new ParametrosFerragemModel();
  public producaoModel: ValorProducaoModel = new ValorProducaoModel();
  public usuarioModel: UsuarioModel = new UsuarioModel();
  public conferentesNome: string[] = [];
  public conferenteSelecionado: string = "";
  public avaliarFerragemForm: FormGroup = new FormGroup({});
  public avaliacao = new AvaliacaoModel();
  public reavaliacao = new ReavaliacaoModel(); 
  public dataSource : ServicosModel[] = [];
  public espacamento?: Boolean;
  public distribuicao?: Boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramFerragemService: ParamFerragemService,
    private centroService: CentroCustoService,
    private producaoService: ValorProducaoService,
    private usuarioService: UsuarioService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }
  ngOnInit(): void {
    this.buscarConferentes();
    this.avaliarFerragemForm = new FormGroup({
      tipoServico: new FormControl('', Validators.required),
      idServico: new FormControl('',Validators.required),
      usuExect: new FormControl('', Validators.required),
      usuConf: new FormControl({value: this.servicoSelecionado.conferente.toString(), disabled: this.indReavaliacao}, Validators.required),
      resultado: new FormControl(null, Validators.required),
      dataAvaliacao: new FormControl('', Validators.required),
      obs: new FormControl(''),
      idAvaliacao: new FormControl('',Validators.required),
      espacamento: new FormControl(null, Validators.required),
      distribuicao: new FormControl(null, Validators.required),
      qtdeAco: new FormControl('', Validators.required),
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

  public async avaliarFerragem() {
    try {
      if (
        !this.avaliarFerragemForm.get('espacamento')?.value == null ||
        !this.avaliarFerragemForm.get('distribuicao')?.value == null ||
        !this.avaliarFerragemForm.get('resultado')?.value == null
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }

      this.paramFerragem.obs = this.avaliarFerragemForm.get('obs')?.value ?? "";
      this.paramFerragem.distribuicao = this.avaliarFerragemForm.get('distribuicao')?.value ?? false;
      this.paramFerragem.espacamento = this.avaliarFerragemForm.get('espacamento')?.value ?? false;
      this.paramFerragem.qtdeAco = this.servicoSelecionado.dimensao;
      
      if (!this.indReavaliacao){ //Avaliação inicial
        this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
        this.avaliacao.idServico = this.servicoSelecionado.idServico;
        this.avaliacao.usuExect = this.servicoSelecionado.executor;
        this.avaliacao.usuConf = this.avaliarFerragemForm.get('usuConf')?.value ?? "";
        this.avaliacao.obs = this.avaliarFerragemForm.get('obs')?.value ?? "";
        this.avaliacao.dataAvaliacao = new Date();
        this.avaliacao.resultado = this.avaliarFerragemForm.get('resultado')?.value ?? false;
  

        await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
        let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
        this.paramFerragem.idAvaliacao = idAvaliacao;
        await lastValueFrom(this.paramFerragemService.salvarParametrosAvaliados(this.paramFerragem));

        if (this.avaliacao.resultado) {
        await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, true, this.avaliacao.usuConf));
        await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
        this.producaoModel.idServico = this.servicoSelecionado.idServico;
        this.producaoModel.idAvaliacao = idAvaliacao;
        let centroSelecionado = await lastValueFrom(this.centroService.buscarPorNome(this.servicoSelecionado.centroDeCusto));
        this.producaoModel.idCentroDeCusto = centroSelecionado.idCentroDeCusto;
        this.usuarioModel = await lastValueFrom(this.usuarioService.buscarPorNome(this.servicoSelecionado.executor));
        this.producaoModel.idUsuario = this.usuarioModel.idUsuario;
        this.producaoModel.mesReferencia = new Date().getMonth() + 1;
        this.producaoModel.anoReferencia = new Date().getFullYear();
        this.producaoModel.valorServico = this.servicoSelecionado.valorTotal;
        await lastValueFrom(this.producaoService.inserirValorProducao(this.producaoModel));
        
        } else {
          await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, false, this.avaliacao.usuConf));
        }
      
      }else { //Reavaliação
        if (this.servicoSelecionado.idAvaliacao) {
          this.reavaliacao.dataReavaliacao = new Date();
          this.reavaliacao.resultReaval = this.avaliarFerragemForm.get('resultado')?.value ?? false;
          this.reavaliacao.idAvaliacao = this.servicoSelecionado.idAvaliacao;
          this.reavaliacao.obs = this.avaliarFerragemForm.get('obs')?.value ?? "";
          this.reavaliacao.conferente = this.servicoSelecionado.conferente;

          await lastValueFrom(this.avaliarService.reavaliar(this.reavaliacao));
          let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
          this.paramFerragem.idAvaliacao = idAvaliacao;
          await lastValueFrom(this.paramFerragemService.salvarParametrosAvaliados(this.paramFerragem));

          if (this.reavaliacao.resultReaval) {
            await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, true, this.reavaliacao.conferente));
            await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
            this.producaoModel.idServico = this.servicoSelecionado.idServico;
            this.producaoModel.idAvaliacao = idAvaliacao;
            let centroSelecionado = await lastValueFrom(this.centroService.buscarPorNome(this.servicoSelecionado.centroDeCusto));
            this.producaoModel.idCentroDeCusto = centroSelecionado.idCentroDeCusto;
            this.usuarioModel = await lastValueFrom(this.usuarioService.buscarPorNome(this.servicoSelecionado.executor));
            this.producaoModel.idUsuario = this.usuarioModel.idUsuario;
            this.producaoModel.mesReferencia = new Date().getMonth() + 1;
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
