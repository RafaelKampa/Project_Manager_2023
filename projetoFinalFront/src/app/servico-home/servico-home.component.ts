import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-servico-home',
  templateUrl: './servico-home.component.html',
  styleUrls: ['./servico-home.component.css']
})
export class ServicoHomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  cadastrarNovoServico() {
    this.router.navigate(['/api/cadastro-servico']);
  }

  listarServicos() {
    this.router.navigate(['/api/listar-servicos']);
  }

  cadastrarCentros() {
    this.router.navigate(['/api/centro-custo']);
  }

}
