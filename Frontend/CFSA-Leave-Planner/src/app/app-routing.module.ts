import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/login/login.component';
import { HomeComponent } from './modules/core/home/home.component';
import { RegisterComponent } from './modules/auth/register/register.component';

const routes: Routes = [
  { path: 'logowanie', component: LoginComponent },
  { path: 'rejestracja', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
