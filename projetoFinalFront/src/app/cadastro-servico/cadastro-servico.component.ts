import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { UsuarioModel } from '../usuario/model/usuario.model';
import { UsuarioService } from '../usuario/service/usuario.service';
import { CadastroServicoModel } from './model/cadastro-servico.model';
import { TipoServicoModel } from './model/tipo-servico.model';
import { CadastroServicoService } from './servico/cadastro-servico.service';
import { CentroCustoModel } from '../shared/models/centro-custo.model';
import { lastValueFrom } from 'rxjs';


@Component({
  selector: 'app-cadastro-servico',
  templateUrl: './cadastro-servico.component.html',
  styleUrls: ['./cadastro-servico.component.css']
})

export class CadastroServicoComponent implements OnInit {

  cadastroServicoForm = new FormGroup({
    tipoServico: new FormControl('',Validators.required),
    valorUnitario: new FormControl({value: '', disabled: true}),
    dimensao: new FormControl('',Validators.required),
    unidadeMedida: new FormControl({value: '', disabled: true}),
    centroDeCusto: new FormControl('',Validators.required),
    localExecucao: new FormControl('',Validators.required),
    executor: new FormControl('', Validators.required),
    conferente: new FormControl('',Validators.required),
    dataInicio: new FormControl('',Validators.required),
    previsaoTermino: new FormControl('',Validators.required),
    obs: new FormControl(''),
  });

  constructor(private cadastroServicosService: CadastroServicoService,
    private usuarioService: UsuarioService,
    private centroCustoServ: CentroCustoService,
    private router: Router,
    private dateAdapter: DateAdapter<Date>) { 
      this.dateAdapter.setLocale('pt-BR')
    }

    public tiposServicos: TipoServicoModel[] = [];
    public tipoServicoSelecionado: TipoServicoModel = new TipoServicoModel();
    public conferenteSelecionado: UsuarioModel[] = [];
    public conferentes: UsuarioModel[] = [];
    public executorSelecionado: UsuarioModel[] = [];
    public executores: UsuarioModel[] = [];
    public centrosDeCusto: CentroCustoModel[] = [];
    public centroSelecionado: CentroCustoModel[] = [];


  ngOnInit() {
    this.listarTipos();
    this.buscarConferentes();
    this.buscarExecutores();
    this.buscarCentrosDeCusto();

  }
  public erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }

  public async listarTipos() {
    this.tiposServicos = await lastValueFrom(this.cadastroServicosService.listarTipos())
  }

  public async buscarConferentes() {
    this.conferentes = await lastValueFrom(this.usuarioService.buscarConferentes())
  }

  public async buscarExecutores() {
    this.executores = await lastValueFrom(this.usuarioService.buscarExecutores());
  }

  public async buscarCentrosDeCusto() {
    this.centrosDeCusto = await lastValueFrom(this.centroCustoServ.listarCentrosDeCusto());
  }

  salvar() {
    let servico = new CadastroServicoModel();
      servico.tipoServico = this.tipoServicoSelecionado.nomeServico;
      servico.valorUnitario = this.tipoServicoSelecionado.valorUnitario;
      servico.dimensao = this.cadastroServicoForm.get('dimensao')?.value;
      servico.unidadeMedida = this.tipoServicoSelecionado.unidadeMedida;
      servico.centroDeCusto = this.centroSelecionado[2];
      servico.localExecucao = this.cadastroServicoForm.get('localExecucao')?.value;
      servico.executor = this.executorSelecionado;
      servico.conferente = this.conferenteSelecionado;
      servico.dataInicio = this.cadastroServicoForm.get('dataInicio')?.value;
      servico.previsaoTermino = this.cadastroServicoForm.get('previsaoTermino')?.value;
      servico.obs = this.cadastroServicoForm.get('obs')?.value;

      this.cadastroServicosService.salvarNovoServico(servico).subscribe(usuarioRetorno => {
        alert("Serviço cadastrado com Sucesso!");
        this.router.navigate(['/api/servico-home']);
      }, (err) => {
        alert("Serviço não cadastrado! \n Contate o Administrador");
        return;
      });
  }

  cancelar() {
    this.router.navigate(['/api/servico-home']);
  }

  numbersOnly(event: { which: any; keyCode: any; }): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode != 46 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }
}

