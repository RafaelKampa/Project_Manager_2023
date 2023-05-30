import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-novo-servico',
  templateUrl: './novo-servico.component.html',
  styleUrls: ['./novo-servico.component.css']
})
export class NovoServicoComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  cadastrarNovoServico() {
    this.router.navigate(['/api/cadastro-servico']);
  }

  listarServicos() {
    this.router.navigate(['/api/listar-servicos']);
  }

}
