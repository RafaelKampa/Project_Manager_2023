import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs/internal/firstValueFrom';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { CentroCustoModel } from '../shared/models/centro-custo.model';

@Component({
  selector: 'app-listar-centros',
  templateUrl: './listar-centros.component.html',
  styleUrls: ['./listar-centros.component.css']
})
export class ListarCentrosComponent implements OnInit {

  displayedColumns: string[] = ['idCentroDeCusto', 'nomeCentroDeCusto', 'enderecoCentroDeCusto', 'valorEmpreendido'];
  lista : CentroCustoModel[] = [];

  constructor(private centroCustoServ: CentroCustoService,
    private router: Router) { }

  async ngOnInit() {
    await this.listarCentrosDeCusto();
  }

  public async listarCentrosDeCusto() {
    this.lista = await firstValueFrom(this.centroCustoServ.listarCentrosDeCusto());
  }

  voltar() {
    this.router.navigate(['/api/servico-home'])
  }
}
