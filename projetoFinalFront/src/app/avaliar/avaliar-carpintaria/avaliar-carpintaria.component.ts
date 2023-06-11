import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { CentroCustoService } from '../../centro-custo/service/centro-custo.service';
import { AvaliacaoModel } from '../../shared/models/avaliacao.model';
import { ParametrosCarpintariaModel } from '../../shared/models/parametros-carpintaria.model';
import { ServicosModel } from '../../shared/models/servico.model';
import { ParamCarpintariaService } from '../../shared/service/param-carpintaria.service';
import { ServicosService } from '../../shared/service/servico.service';
import { AvaliarService } from '../service/avaliar.service';
import { ReavaliacaoModel } from '../reavaliar/model/reavaliacao.model';
import { ValorProducaoModel } from '../../shared/models/valor-producao.model';
import { UsuarioModel } from '../../usuario/model/usuario.model';
import { CentroCustoModel } from '../../shared/models/centro-custo.model';
import { ValorProducaoService } from '../../shared/service/valor-producao.service';
import { UsuarioService } from '../../usuario/service/usuario.service';

@Component({
  selector: 'app-avaliar-carpintaria',
  templateUrl: './avaliar-carpintaria.component.html',
  styleUrls: ['./avaliar-carpintaria.component.css']
})
export class AvaliarCarpintariaComponent {

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();
  
  @Input()
  public indReavaliacao: Boolean = false;

  public avaliacao = new AvaliacaoModel();
  public reavaliacao = new ReavaliacaoModel(); 
  public paramCarpintaria = new ParametrosCarpintariaModel();
  public producaoModel: ValorProducaoModel = new ValorProducaoModel();
  public usuarioModel: UsuarioModel = new UsuarioModel();
  public centroDeCustoModel: CentroCustoModel = new CentroCustoModel();
  public nivelOuPrumo?: boolean;
  public estanqueidade?: boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramCarpintariaService: ParamCarpintariaService,
    private centroService: CentroCustoService,
    private producaoService: ValorProducaoService,
    private usuarioService: UsuarioService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

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

  public erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }
 
  public async avaliarCarpintaria() {
    try {
      if (
        this.avaliarCarpintariaForm.get('tipoCarpintaria')?.value == null ||
        this.avaliarCarpintariaForm.get('nivelOuPrumo')?.value == null ||
        this.avaliarCarpintariaForm.get('estanqueidade')?.value == null ||
        this.avaliarCarpintariaForm.get('resultado')?.value == null 
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }

      this.paramCarpintaria.obs = this.avaliarCarpintariaForm.get('obs')?.value ?? "";
      this.paramCarpintaria.estanqueidade = this.avaliarCarpintariaForm.get('estanqueidade')?.value ?? false;
      this.paramCarpintaria.nivelOuPrumo = this.avaliarCarpintariaForm.get('nivelOuPrumo')?.value ?? false;
      this.paramCarpintaria.dimensoes = this.servicoSelecionado.dimensao;
      this.paramCarpintaria.tipoCarpintaria = this.avaliarCarpintariaForm.get('tipoCarpintaria')?.value ?? "";

      if (!this.indReavaliacao){ //Avaliação inicial
        this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
        this.avaliacao.idServico = this.servicoSelecionado.idServico;
        this.avaliacao.usuExect = this.servicoSelecionado.executor;
        this.avaliacao.usuConf = this.servicoSelecionado.conferente;
        this.avaliacao.obs = this.avaliarCarpintariaForm.get('obs')?.value ?? "";
        this.avaliacao.dataAvaliacao = new Date();
        this.avaliacao.resultado = this.avaliarCarpintariaForm.get('resultado')?.value ?? false;
  
        await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
        let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
        this.paramCarpintaria.idAvaliacao = idAvaliacao;
        await lastValueFrom(this.paramCarpintariaService.salvarParametrosAvaliados(this.paramCarpintaria));

        if (this.avaliacao.resultado) {
          await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, true));
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
          await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, false));
        }
      
      } else { //Reavaliação
        if (this.servicoSelecionado.idAvaliacao) {
          this.reavaliacao.dataReavaliacao = new Date();
          this.reavaliacao.resultReaval = this.avaliarCarpintariaForm.get('resultado')?.value ?? false;
          this.reavaliacao.idAvaliacao = this.servicoSelecionado.idAvaliacao;
          this.reavaliacao.obs = this.avaliarCarpintariaForm.get('obs')?.value ?? "";

          await lastValueFrom(this.avaliarService.reavaliar(this.reavaliacao));
          let idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
          this.paramCarpintaria.idAvaliacao = idAvaliacao;
          await lastValueFrom(this.paramCarpintariaService.salvarParametrosAvaliados(this.paramCarpintaria));

          if (this.reavaliacao.resultReaval) {
            await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, true));
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
            await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico, false));
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