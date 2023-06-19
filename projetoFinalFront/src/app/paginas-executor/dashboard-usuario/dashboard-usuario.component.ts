import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { ValorProducaoModel } from '../../shared/models/valor-producao.model';
import { ValorProducaoService } from '../../shared/service/valor-producao.service';

declare var CanvasJS: any;

@Component({
  selector: 'app-dashboard-usuario',
  templateUrl: './dashboard-usuario.component.html',
  styleUrls: ['./dashboard-usuario.component.css']
})
export class DashboardUsuarioComponent implements OnInit, AfterViewInit {
  public producao: ValorProducaoModel[] = [];
  public idUsuario: number = 0;
  public chartOptions: any;

  @ViewChild('chartContainer') chartContainer: any;

  constructor(private producaoServ: ValorProducaoService) {}

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token != null) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      this.idUsuario = payload.userId;
    }
    this.buscarProducaoMensal(this.idUsuario);
  }

  ngAfterViewInit() {
    this.initChart();
  }

  public buscarProducaoMensal(idUsuario: number) {
    this.producaoServ.listarProducaoPorUsuario(idUsuario).subscribe(producao => {
      this.producao = producao;
      this.updateChart();
    });
  }

  private initChart() {
	this.chartOptions = {
	  theme: "light2",
	  animationEnabled: true,
	  zoomEnabled: true,
	  title: {
		text: "Produção por mês"
	  },
	  axisY: {
		labelFormatter: (e: any) => {
		  var suffixes = ["", "K", "M", "B", "T"];
  
		  var order = Math.max(Math.floor(Math.log(e.value) / Math.log(1000)), 0);
		  if (order > suffixes.length - 1)
			order = suffixes.length - 1;
  
		  var suffix = suffixes[order];
		  return "R$" + (e.value / Math.pow(1000, order)) + suffix;
		}
	  },
	  data: [{
		type: "line",
		xValueFormatString: "MMM YYYY",
		yValueFormatString: "R$ #",
		dataPoints: []
	  }]
	};
  
	const chart = new CanvasJS.Chart(this.chartContainer.nativeElement, this.chartOptions);
	chart.render();
  }

  private updateChart() {
	if (this.chartOptions && this.chartOptions.data && this.chartOptions.data[0]) {
	  const dataPoints = this.producao.map((elemento) => ({
		x: new Date(elemento.anoReferencia, elemento.mesReferencia - 1, 1),
		y: elemento.valorTotal
	  }));
  
	  this.chartOptions.data[0].dataPoints = dataPoints;
  
	  const chart = new CanvasJS.Chart(this.chartContainer.nativeElement, this.chartOptions);
	  chart.render();
	}
  }
  
}
