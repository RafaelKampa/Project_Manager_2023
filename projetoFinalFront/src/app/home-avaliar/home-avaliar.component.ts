import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

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
