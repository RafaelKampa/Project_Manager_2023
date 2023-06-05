import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-avaliar',
  templateUrl: './home-avaliar.component.html',
  styleUrls: ['./home-avaliar.component.css']
})
export class HomeAvaliarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  avaliar() {
    this.router.navigate(['/api/avaliar']);
  }

  reavaliar() {
    this.router.navigate(['/api/reavaliar']);
  }
}
