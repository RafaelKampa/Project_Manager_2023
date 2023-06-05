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

@Component({
  selector: 'app-avaliar-alvenaria',
  templateUrl: './avaliar-alvenaria.component.html',
  styleUrls: ['./avaliar-alvenaria.component.css']
})
export class AvaliarAlvenariaComponent {

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();

  public paramAlvenaria = new ParametrosAlvenariaModel();
  public avaliacao = new AvaliacaoModel();
  public dataSource : ServicosModel[] = [];
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
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

    avaliarAlvenariaForm = new FormGroup ({
    tipoServico: new FormControl('', Validators.required),
    idServico: new FormControl('',Validators.required),
    usuExect: new FormControl('', Validators.required),
    usuConf: new FormControl('', Validators.required),
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

  public erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }

  public async avaliarAlvenaria() {
    try {
      if (
        !this.avaliarAlvenariaForm.get('prumo')?.value ||
        !this.avaliarAlvenariaForm.get('nivel')?.value ||
        !this.avaliarAlvenariaForm.get('alinhamento')?.value ||
        !this.avaliarAlvenariaForm.get('integridade')?.value ||
        !this.avaliarAlvenariaForm.get('limpeza')?.value ||
        !this.avaliarAlvenariaForm.get('resultado')?.value
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }
      this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
      this.avaliacao.idServico = this.servicoSelecionado.idServico;
      this.avaliacao.usuExect = this.servicoSelecionado.executor;
      this.avaliacao.usuConf = this.servicoSelecionado.conferente;
      this.avaliacao.obs = this.avaliarAlvenariaForm.get('obs')?.value ?? "";
      this.avaliacao.dataAvaliacao = new Date();
      this.avaliacao.resultado = this.avaliarAlvenariaForm.get('resultado')?.value ?? false;

      this.paramAlvenaria.obs = this.avaliarAlvenariaForm.get('obs')?.value ?? "";
      this.paramAlvenaria.prumo = this.avaliarAlvenariaForm.get('prumo')?.value ?? false;
      this.paramAlvenaria.nivel = this.avaliarAlvenariaForm.get('nivel')?.value ?? false;
      this.paramAlvenaria.alinhamento = this.avaliarAlvenariaForm.get('alinhamento')?.value ?? false;
      this.paramAlvenaria.integridade = this.avaliarAlvenariaForm.get('integridade')?.value ?? false;
      this.paramAlvenaria.limpeza = this.avaliarAlvenariaForm.get('limpeza')?.value ?? false;
      this.paramAlvenaria.dimensoes = this.servicoSelecionado.dimensao;
      await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
      this.paramAlvenaria.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramAlvenariaService.salvarParametrosAvaliados(this.paramAlvenaria));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico));
      await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
      alert("Serviço Avaliado com Sucesso!");
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
    }
  }
}
