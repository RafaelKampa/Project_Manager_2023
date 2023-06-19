import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  isSidebarOpen = false;

  constructor(private router: Router) { }

  toggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  ngOnInit(): void {
  }
  showFiller = false;

  cadastrarNovoServico() {
    this.router.navigate(['/api/cadastro-servico']);
  }

  listarServicos() {
    this.router.navigate(['/api/listar-servicos']);
  }

  cadastrarCentros() {
    this.router.navigate(['/api/centro-custo']);
  }

  listarCentros() {
    this.router.navigate(['/api/listar-centros']);
  }

  avaliarServicos() {
    this.router.navigate(['/api/avaliar']);
  }

  reavaliarServicos() {
    this.router.navigate(['/api/reavaliar']);
  }
}
