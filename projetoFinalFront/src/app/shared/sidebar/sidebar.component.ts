import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthguardService } from '../authguard.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {

  authorities: string[] | null = null;

  constructor(private router: Router,
    private authguardService: AuthguardService) {
      this.authguardService.authorities$.subscribe(authority => {
        this.authorities = authority;
     })
    }

  ngOnInit(): void {
  }
  servicos() {
    this.router.navigate(['/api/servicos/']);
  }

  isSidebarOpen = false;

  publictoggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
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
