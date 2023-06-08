import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-centro-de-custos',
  templateUrl: './centro-de-custos.component.html',
  styleUrls: ['./centro-de-custos.component.css']
})
export class CentroDeCustosComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  cadastrarCentros() {
    this.router.navigate(['/api/centro-custo']);
  }

  listarCentros() {
    this.router.navigate(['/api/listar-centros']);
  }

}
