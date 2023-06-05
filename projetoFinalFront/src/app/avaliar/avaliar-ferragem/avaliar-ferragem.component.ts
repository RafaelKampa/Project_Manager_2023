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

@Component({
  selector: 'app-avaliar-ferragem',
  templateUrl: './avaliar-ferragem.component.html',
  styleUrls: ['./avaliar-ferragem.component.css']
})
export class AvaliarFerragemComponent {

  @Input()
  public servicoSelecionado: ServicosModel = new ServicosModel();

  public paramFerragem = new ParametrosFerragemModel();
  public avaliacao = new AvaliacaoModel();
  public dataSource : ServicosModel[] = [];
  public espacamento?: Boolean;
  public distribuicao?: Boolean;
  public resultado?: Boolean;

  constructor(private servicosService: ServicosService,
    private avaliarService: AvaliarService,
    private paramFerragemService: ParamFerragemService,
    private centroService: CentroCustoService,
    private router: Router,    
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

    avaliarFerragemForm = new FormGroup({
      tipoServico: new FormControl('', Validators.required),
      idServico: new FormControl('',Validators.required),
      usuExect: new FormControl('', Validators.required),
      usuConf: new FormControl('', Validators.required),
      resultado: new FormControl(null, Validators.required),
      dataAvaliacao: new FormControl('', Validators.required),
      obs: new FormControl(''),
      idAvaliacao: new FormControl('',Validators.required),
      espacamento: new FormControl(null, Validators.required),
      distribuicao: new FormControl(null, Validators.required),
      qtdeAco: new FormControl('', Validators.required),
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
  public async avaliarFerragem() {
    try {
      if (
        !this.avaliarFerragemForm.get('espacamento')?.value ||
        !this.avaliarFerragemForm.get('distribuicao')?.value ||
        !this.avaliarFerragemForm.get('resultado')?.value
      ) {
        alert("Preencha todos os campos antes de avaliar.");
        return;
      }
      this.avaliacao.tipoServico = this.servicoSelecionado.tipoServico;
      this.avaliacao.idServico = this.servicoSelecionado.idServico;
      this.avaliacao.usuExect = this.servicoSelecionado.executor;
      this.avaliacao.usuConf = this.servicoSelecionado.conferente;
      this.avaliacao.obs = this.avaliarFerragemForm.get('obs')?.value ?? "";
      this.avaliacao.dataAvaliacao = new Date();
      this.avaliacao.resultado = this.avaliarFerragemForm.get('resultado')?.value ?? false;

      this.paramFerragem.obs = this.avaliarFerragemForm.get('obs')?.value ?? "";
      this.paramFerragem.distribuicao = this.avaliarFerragemForm.get('distribuicao')?.value ?? false;
      this.paramFerragem.espacamento = this.avaliarFerragemForm.get('espacamento')?.value ?? false;
      this.paramFerragem.qtdeAco = this.servicoSelecionado.dimensao;
      this.avaliacao.resultado = this.avaliarFerragemForm.get('resultado')?.value ?? false;
      await lastValueFrom(this.avaliarService.avaliar(this.avaliacao));
      this.paramFerragem.idAvaliacao = await lastValueFrom(this.avaliarService.buscarUltimoId())
      await lastValueFrom(this.paramFerragemService.salvarParametrosAvaliados(this.paramFerragem));
      await lastValueFrom(this.servicosService.concluirServico(this.servicoSelecionado.idServico));
      await lastValueFrom(this.centroService.incluirValor(this.servicoSelecionado.centroDeCusto, this.servicoSelecionado.valorTotal));
      alert("Serviço Avaliado com Sucesso!");
      this.router.navigate(['/api/servico-home']);
    } catch{
      alert("Serviço não avaliado!\nContate o Administrador");
    }
  }
}