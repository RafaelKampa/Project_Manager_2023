import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-avaliacao',
  templateUrl: './avaliacao.component.html',
  styleUrls: ['./avaliacao.component.css']
})
export class AvaliacaoComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  avaliarServicos() {
    this.router.navigate(['/api/avaliar']);
  }

  reavaliarServicos() {
    this.router.navigate(['/api/reavaliar']);
  }
}
