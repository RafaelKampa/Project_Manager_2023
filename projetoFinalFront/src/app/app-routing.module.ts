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
import { ReavaliarComponent } from './avaliar/reavaliar/reavaliar.component';
import { CentroDeCustosComponent } from './centro-de-custos/centro-de-custos.component';
import { HomeAvaliarComponent } from './home-avaliar/home-avaliar.component';
import { ServicosComponent } from './servicos/servicos.component';
import { FinanceiroComponent } from './financeiro/financeiro.component';
import { ProducaoComponent } from './paginas-executor/producao/producao.component';
import { AvaliacoesComponent } from './paginas-executor/avaliacoes/avaliacoes.component';

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
      { path: 'home', component: HomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'usuario', component: UsuarioComponent },
      { path: 'cadastro-servico', component: CadastroServicoComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'servico-home', component: ServicoHomeComponent, canActivate: [AuthGuard], data: { authorities: ['1', '2'] } },
      { path: 'centro-custo', component: CentroCustoComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'listar-servicos', component: ListarServicosComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'listar-centros', component: ListarCentrosComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'avaliar', component: AvaliarComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'reavaliar', component: ReavaliarComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'centro-de-custos', component: CentroDeCustosComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'home-avaliar', component: HomeAvaliarComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'servicos', component: ServicosComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'financeiro', component: FinanceiroComponent, canActivate: [AuthGuard], data: { authorities: ['1'] } },
      { path: 'producao', component: ProducaoComponent, canActivate: [AuthGuard], data: { authorities: ['2'] } },
      { path: 'avaliacoes', component: AvaliacoesComponent, canActivate: [AuthGuard], data: { authorities: ['2'] } },
      { path: '**', redirectTo: 'home' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
