import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TipoServicoModel } from './model/tipo-servico.model';
import { CadastroServicoService } from './servico/cadastro-servico.service';

@Component({
  selector: 'app-cadastro-servico',
  templateUrl: './cadastro-servico.component.html',
  styleUrls: ['./cadastro-servico.component.css']
})
export class CadastroServicoComponent implements OnInit {

  public tiposServicos: TipoServicoModel[] = [];

  cadastroServicoForm = new FormGroup({
    username: new FormControl('',Validators.required),
    nome: new FormControl('',Validators.required),
    dataNascimento: new FormControl('',Validators.required),
    cpf: new FormControl('',Validators.required),
    enderecoResidencial: new FormControl('',Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    contratante: new FormControl('',Validators.required),
    dataAdmissao: new FormControl('',Validators.required),
    cargo: new FormControl('',Validators.required),
    remuneracao: new FormControl('',Validators.required),
    telefone: new FormControl(''),
    senha: new FormControl('',Validators.required),
    confirmarSenha: new FormControl('',Validators.required)
  });

  constructor(private cadastroServicosService: CadastroServicoService) { }

  async ngOnInit(): Promise<void> {
    this.listarTipos();
  }


  public async listarTipos() {
    this.tiposServicos = await this.cadastroServicosService.listarTipos().toPromise();
  }
}
