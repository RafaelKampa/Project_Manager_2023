import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public title = 'ProjectManager';

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
