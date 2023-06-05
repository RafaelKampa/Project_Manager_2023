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

@Component({
  selector: 'app-avaliar-carpintaria',
  templateUrl: './avaliar-carpintaria.component.html',
  styleUrls: ['./avaliar-carpintaria.component.css']
})
export class AvaliarCarpintariaComponent {

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();

  public avaliacao = new AvaliacaoModel();
  public paramCarpintaria = new ParametrosCarpintariaModel();
  public nivelOuPrumo?: boolean;
  public estanqueidade?: boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramCarpintariaService: ParamCarpintariaService,
    private centroService: CentroCustoService,
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
        !this.avaliarCarpintariaForm.get('tipoCarpintaria')?.value ||
        !this.avaliarCarpintariaForm.get('nivelOuPrumo')?.value ||
        !this.avaliarCarpintariaForm.get('estanqueidade')?.value ||
        !this.avaliarCarpintariaForm.get('resultado')?.value
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }
      this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
      this.avaliacao.idServico = this.servicoSelecionado.idServico;
      this.avaliacao.usuExect = this.servicoSelecionado.executor;
      this.avaliacao.usuConf = this.servicoSelecionado.conferente;
      this.avaliacao.obs = this.avaliarCarpintariaForm.get('obs')?.value ?? "";
      this.avaliacao.dataAvaliacao = new Date();
      this.avaliacao.resultado = this.avaliarCarpintariaForm.get('resultado')?.value ?? false;

      this.paramCarpintaria.obs = this.avaliarCarpintariaForm.get('obs')?.value ?? "";
      this.paramCarpintaria.estanqueidade = this.avaliarCarpintariaForm.get('estanqueidade')?.value ?? false;
      this.paramCarpintaria.nivelOuPrumo = this.avaliarCarpintariaForm.get('nivelOuPrumo')?.value ?? false;
      this.paramCarpintaria.dimensoes = this.servicoSelecionado.dimensao;
      this.paramCarpintaria.tipoCarpintaria = this.avaliarCarpintariaForm.get('tipoCarpintaria')?.value ?? "";
      this.avaliacao.resultado = this.avaliarCarpintariaForm.get('resultado')?.value ?? false;
      await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
      this.paramCarpintaria.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramCarpintariaService.salvarParametrosAvaliados(this.paramCarpintaria));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico));
      await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
      alert("Serviço Avaliado com Sucesso!");
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
    }
  }
}