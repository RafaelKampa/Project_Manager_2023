import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-centro-de-custo',
  templateUrl: './centro-de-custo.component.html',
  styleUrls: ['./centro-de-custo.component.css']
})
export class CentroDeCustoComponent implements OnInit {

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
