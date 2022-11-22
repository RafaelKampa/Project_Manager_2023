import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CadastroServicoModel } from './model/cadastro-servico.model';
import { TipoServicoModel } from './model/tipo-servico.model';
import { CadastroServicoService } from './servico/cadastro-servico.service';

@Component({
  selector: 'app-cadastro-servico',
  templateUrl: './cadastro-servico.component.html',
  styleUrls: ['./cadastro-servico.component.css']
})
export class CadastroServicoComponent implements OnInit {
  selectedValue: string = "";

  public servicoSelecionado: any;

  cadastroServicoForm = new FormGroup({
    valorUnitario: new FormControl('',Validators.required),
    dimensao: new FormControl('',Validators.required),
    centroDeCusto: new FormControl('',Validators.required),
    localExecucao: new FormControl('',Validators.required),
    executor: new FormControl('', [Validators.required, Validators.email]),
    conferente: new FormControl('',Validators.required),
    dataInicio: new FormControl('',Validators.required),
    dataPrevista: new FormControl('',Validators.required),
    valorTotal: new FormControl('',Validators.required),
  });

  constructor(private cadastroServicosService: CadastroServicoService,
    private router: Router) { }

    public tiposServicos: TipoServicoModel[] = [];

  ngOnInit() {
    this.listarTipos();
  }
  public erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    } else {
      return;
    }
  }

  listarTipos() {
    this.cadastroServicosService.listarTipos().subscribe(tipos => {
      this.tiposServicos = tipos;
      
    });
  }

  salvar() {
    let servico = new CadastroServicoModel();
      if (this.cadastroServicoForm.valid){
      servico.valorUnitario = this.cadastroServicoForm.get('valorUnitario')?.value;
      servico.dimensao = this.cadastroServicoForm.get('dimensao')?.value;
      servico.centroDeCusto = this.cadastroServicoForm.get('centroDeCusto')?.value;
      servico.localExecucao = this.cadastroServicoForm.get('localExecucao')?.value;
      servico.executor = this.cadastroServicoForm.get('executor')?.value;
      servico.conferente = this.cadastroServicoForm.get('conferente')?.value;
      servico.dataInicio = this.cadastroServicoForm.get('dataInicio')?.value;
      servico.dataPrevista = this.cadastroServicoForm.get('dataPrevista')?.value;
      servico.valorTotal = this.cadastroServicoForm.get('cargo')?.value;

      this.cadastroServicosService.salvarNovoServico(servico).subscribe(usuarioRetorno => {
        alert("Usuário Cadastrado! Agora faça o seu login...");
        this.router.navigate(['/api/login']);
      }, err => {
        alert(err);
        return;
      });
    }
  }
}
