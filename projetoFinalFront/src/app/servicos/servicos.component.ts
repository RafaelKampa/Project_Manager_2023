import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-servicos',
  templateUrl: './servicos.component.html',
  styleUrls: ['./servicos.component.css']
})
export class ServicosComponent implements OnInit {

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
