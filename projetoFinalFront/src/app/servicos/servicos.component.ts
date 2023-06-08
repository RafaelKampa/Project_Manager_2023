import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

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
