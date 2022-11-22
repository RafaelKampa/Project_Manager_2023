import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CentroCustoModel } from '../centro-custo/model/centro-custo.model';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { UsuarioModel } from '../usuario/model/usuario.model';
import { UsuarioService } from '../usuario/service/usuario.service';
import { CadastroServicoModel } from './model/cadastro-servico.model';
import { TipoServicoModel } from './model/tipo-servico.model';
import { CadastroServicoService } from './servico/cadastro-servico.service';


@Component({
  selector: 'app-cadastro-servico',
  templateUrl: './cadastro-servico.component.html',
  styleUrls: ['./cadastro-servico.component.css']
})

export class CadastroServicoComponent implements OnInit {

  cadastroServicoForm = new FormGroup({
    tipoServico: new FormControl('',Validators.required),
    valorUnitario: new FormControl({value: '', disabled: true}),
    dimensoes: new FormControl('',Validators.required),
    unidadeMedida: new FormControl({value: '', disabled: true}),
    centroDeCusto: new FormControl('',Validators.required),
    localExecucao: new FormControl('',Validators.required),
    executor: new FormControl('', Validators.required),
    conferente: new FormControl('',Validators.required),
    dataInicio: new FormControl('',Validators.required),
    dataPrevista: new FormControl('',Validators.required),
    obs: new FormControl('',Validators.required),
  });

  constructor(private cadastroServicosService: CadastroServicoService,
    private usuarioService: UsuarioService,
    private centroCustoServ: CentroCustoService) { }

    public tiposServicos: any;
    public tipoServicoSelecionado: TipoServicoModel[] = [];
    public conferenteSelecionado: UsuarioModel[] = [];
    public conferentes: any;
    public executorSelecionado: UsuarioModel[] = [];
    public executores: any;
    public centrosDeCusto: any;
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
    this.cadastroServicosService.listarTipos().subscribe(tipos => {
      this.tiposServicos = tipos
    });
  }

  public buscarConferentes() {
    this.usuarioService.buscarConferentes().subscribe(conferentes => {
      this.conferentes = conferentes
    });
  }

  public buscarExecutores() {
    this.usuarioService.buscarExecutores().subscribe(executores => {
      this.executores = executores
    });
  }

  public buscarCentrosDeCusto() {
    this.centroCustoServ.listarCentrosDeCusto().subscribe(centros => {
      this.centrosDeCusto = centros
    });
  }

  salvar() {
    let servico = new CadastroServicoModel();
      servico.tipoServico = this.cadastroServicoForm.get('tipoServico')?.value;
      servico.valorUnitario = this.tipoServicoSelecionado[3];
      servico.dimensoes = this.cadastroServicoForm.get('dimensoes')?.value;
      servico.unidadeMedida = this.tipoServicoSelecionado[2];
      servico.centroDeCusto = this.centroSelecionado[1];
      servico.localExecucao = this.cadastroServicoForm.get('localExecucao')?.value;
      servico.executor = this.cadastroServicoForm.get('executor')?.value;
      servico.conferente = this.conferenteSelecionado[10];
      servico.dataInicio = this.cadastroServicoForm.get('dataInicio')?.value;
      servico.dataPrevista = this.cadastroServicoForm.get('dataPrevista')?.value;
      servico.obs = this.cadastroServicoForm.get('obs')?.value;

      this.cadastroServicosService.salvarNovoServico(servico).subscribe(usuarioRetorno => {
        console.log("SALVOU!")
        alert("Serviço cadastrado com Sucesso!");
      }, err => {
        alert(err);
        return;
      });
  }
}
