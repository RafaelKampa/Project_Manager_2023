import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public get opcoesLogin(): boolean {
    if (this.router.isActive('api/home', true)) {
      return true;
      } 
    if (this.router.isActive('api/login', true)) {
    return true;
    } 
    if (this.router.isActive('api/usuario', true)) {
      return true;
    } 
    return false;
  }

  public home() {
    this.router.navigate(["api/home"]);
  }

  public logout() {
    localStorage.clear();
    this.router.navigate(["api/home"]);
  }

}
