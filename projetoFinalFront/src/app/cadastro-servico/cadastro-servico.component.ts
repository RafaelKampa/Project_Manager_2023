import { Component, OnInit } from '@angular/core';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { UsuarioModel } from '../usuario/model/usuario.model';
import { UsuarioService } from '../usuario/service/usuario.service';
import { CentroCustoModel } from '../shared/models/centro-custo.model';
import { lastValueFrom } from 'rxjs';
import { ServicosModel } from '../shared/models/servico.model';
import { TipoServicoModel } from '../shared/models/tipo-servico-model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServicosService } from '../shared/service/servico.service';



@Component({
  selector: 'app-cadastro-servico',
  templateUrl: './cadastro-servico.component.html',
  styleUrls: ['./cadastro-servico.component.css']
})

export class CadastroServicoComponent implements OnInit {

  cadastroServicoForm = new FormGroup({
    tipoServico: new FormControl('',Validators.required),
    valorUnitario: new FormControl({value: '', disabled: true}),
    dimensao: new FormControl({value: '', disabled: false}, Validators.required),
    unidadeMedida: new FormControl({value: '', disabled: true}),
    centroDeCusto: new FormControl('',Validators.required),
    localExecucao: new FormControl('',Validators.required),
    executor: new FormControl('', Validators.required),
    conferente: new FormControl('',Validators.required),
    dataInicio: new FormControl('',Validators.required),
    previsaoTermino: new FormControl('', Validators.required),
    obs: new FormControl(''),
  });

  constructor(private cadastroServicosService: ServicosService,
    private usuarioService: UsuarioService,
    private centroCustoServ: CentroCustoService,
    private router: Router) { 
    }

    public tiposServicos: TipoServicoModel[] = [];
    public tipoServicoSelecionado: TipoServicoModel = new TipoServicoModel();
    public conferenteSelecionado: UsuarioModel = new UsuarioModel();
    public conferentes: UsuarioModel[] = [];
    public executorSelecionado: UsuarioModel = new UsuarioModel();
    public executores: UsuarioModel[] = [];
    public centrosDeCusto: CentroCustoModel[] = [];
    public centroSelecionado: CentroCustoModel = new CentroCustoModel();


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

  public selecionarTipo(event: any) {
    this.tipoServicoSelecionado = event.value;
  }

  public selecionarCentro(event: any) {
    this.centroSelecionado = event.value;
  }

  public selecionarExecutor(event: any) {
    this.executorSelecionado = event.value;
  }

  salvar() {
    let servico = new ServicosModel();
      servico.tipoServico = this.tipoServicoSelecionado.nomeServico;
      servico.valorUnitario = this.tipoServicoSelecionado.valorUnitario;
      var dimensaoControl = this.cadastroServicoForm.get('dimensao')?.value;
      if (dimensaoControl) {
        servico.dimensao = +dimensaoControl;
      }
      servico.unidadeMedida = this.tipoServicoSelecionado.unidadeMedida;
      servico.centroDeCusto = this.centroSelecionado.nomeCentroDeCusto;
      var localControl = this.cadastroServicoForm.get('localExecucao')?.value;
      if (localControl) {
        servico.localExecucao = localControl;
      }
      servico.executor = this.executorSelecionado.nome;
      servico.conferente = this.conferenteSelecionado.nome;
      var dataInicioControl = this.cadastroServicoForm.get('dataInicio')?.value;
      if(dataInicioControl) {
        servico.dataInicio = new Date(dataInicioControl);
      }
      var previsaoTerminoControl = this.cadastroServicoForm.get('previsaoTermino')?.value;
      if(previsaoTerminoControl) {
        servico.previsaoTermino = new Date(previsaoTerminoControl);
        
      }
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

