import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {

  constructor(private router: Router) { }


  public get mostrarSidebar(): boolean {
    if (this.router.isActive('api/home', true)) {
      return false;
      } 
    if (this.router.isActive('api/login', true)) {
    return false;
    } 
    if (this.router.isActive('api/usuario', true)) {
      return false;
    } 
    return true;
  }
}
