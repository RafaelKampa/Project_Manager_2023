import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CentroCustoService } from '../centro-custo/service/centro-custo.service';
import { ListarCentrosModel } from './model/listar-centros.model';

@Component({
  selector: 'app-listar-centros',
  templateUrl: './listar-centros.component.html',
  styleUrls: ['./listar-centros.component.css']
})
export class ListarCentrosComponent implements OnInit {

  displayedColumns: string[] = ['idCentroDeCusto', 'nomeCentroDeCusto', 'enderecoCentroDeCusto', 'valorEmpreendido'];
  dataSource : ListarCentrosModel[] = [];

  constructor(private centroCustoServ: CentroCustoService,
    private router: Router) { }

  ngOnInit(): void {
    this.listarCentrosDeCusto();
  }

  public listarCentrosDeCusto() {
    this.centroCustoServ.listarCentrosDeCusto().subscribe(centros => {
      this.dataSource = centros
    });
  }

  voltar() {
    this.router.navigate(['/api/servico-home'])
  }
}
