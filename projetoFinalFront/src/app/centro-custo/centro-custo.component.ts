import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CentroCustoModel } from './model/centro-custo.model';
import { CentroCustoService } from './service/centro-custo.service';

@Component({
  selector: 'app-centro-custo',
  templateUrl: './centro-custo.component.html',
  styleUrls: ['./centro-custo.component.css']
})
export class CentroCustoComponent implements OnInit {

  constructor(private centroCustoServ: CentroCustoService,
    private router: Router) { }

  ngOnInit(): void {
  }

  cadastroCentroForm = new FormGroup({
    nomeCentroDeCusto: new FormControl('',Validators.required),
    enderecoCentroDeCusto: new FormControl('',Validators.required)
  });

  erroCampoVazio = new FormControl('', Validators.required);
  getErrorMessage() {
    if (this.erroCampoVazio.hasError('required')) {
      return 'Campo obrigatório';
    }
    return;
  }

  salvar() {
    let centro = new CentroCustoModel();
      if (this.cadastroCentroForm.valid){
      centro.nomeCentroDeCusto = this.cadastroCentroForm.get('nomeCentroDeCusto')?.value;
      centro.enderecoCentroDeCusto = this.cadastroCentroForm.get('enderecoCentroDeCusto')?.value;

      this.centroCustoServ.salvarNovoCentro(centro).subscribe(centroRetorno => {
        alert("Centro de custo Cadastrado");
        this.router.navigate(['/api/servico-home']);
      }, (err) => {
        alert("Centro não cadastrado! \n Contate o Administrador");
        return;
      });
    }
  }
  cancelar() {
    this.router.navigate(['/api/servico-home']);
  }

}
