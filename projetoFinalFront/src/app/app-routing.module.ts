import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioComponent } from './usuario/usuario.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './shared/authguard.guard';
import { CadastroServicoComponent } from './cadastro-servico/cadastro-servico.component';
import { ServicoHomeComponent } from './servico-home/servico-home.component';
import { CentroCustoComponent } from './centro-custo/centro-custo.component';
import { ListarServicosComponent } from './listar-servicos/listar-servicos.component';
import { ListarCentrosComponent } from './listar-centros/listar-centros.component';
import { AvaliarComponent } from './avaliar/avaliar.component';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: '',
    redirectTo: '/api/home',
    pathMatch: 'full',
  },
  {
    path: 'api',
    component: LayoutComponent,
    children: [
      {path: 'home', component: HomeComponent},
      { path: 'login', component: LoginComponent },
      { path: 'usuario', component: UsuarioComponent },
      { path: 'cadastro-servico', component: CadastroServicoComponent, canActivate: [AuthGuard]},
      { path: 'servico-home', component: ServicoHomeComponent, canActivate: [AuthGuard]},
      { path: 'centro-custo', component: CentroCustoComponent, canActivate: [AuthGuard]},
      { path: 'listar-servicos', component: ListarServicosComponent, canActivate: [AuthGuard]},
      { path: 'listar-centros', component: ListarCentrosComponent, canActivate: [AuthGuard]},
      { path: 'avaliar', component: AvaliarComponent, canActivate: [AuthGuard]},
      { path: '**', redirectTo: 'home' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
