import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioComponent } from './usuario/usuario.component';
import { LoginComponent } from './login/login.component';
import { LayoutComponent } from './layout/layout.component';
import { AuthGuard } from './shared/authguard.guard';
import { CadastroServicoComponent } from './cadastro-servico/cadastro-servico.component';
import { ServicoHomeComponent } from './servico-home/servico-home.component';
import { CentroCustoComponent } from './centro-custo/centro-custo.component';
import { ListarServicosComponent } from './listar-servicos/listar-servicos.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '',
    redirectTo: '/api/login',
    pathMatch: 'full',
  },
  {
    path: 'api',
    component: LayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'usuario', component: UsuarioComponent },
      { path: 'cadastro-servico', component: CadastroServicoComponent, canActivate: [AuthGuard]},
      { path: 'servico-home', component: ServicoHomeComponent, canActivate: [AuthGuard]},
      { path: 'centro-custo', component: CentroCustoComponent, canActivate: [AuthGuard]},
      { path: 'listar-servicos', component: ListarServicosComponent, canActivate: [AuthGuard]},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
