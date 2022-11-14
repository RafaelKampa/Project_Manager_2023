import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '',
    redirectTo: '/api/home',
    pathMatch: 'full',
  },
  {
    path: 'api',
    component: LayoutComponent,
    children: [
      { path: 'home', component: LoginComponent },
      { path: 'usuario', component: UsuarioComponent },
      { path: 'cliente', component: ClienteComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
