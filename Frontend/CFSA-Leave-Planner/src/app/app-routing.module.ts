import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/components/login/login.component';
import { HomeComponent } from './modules/core/components/home/home.component';
import { RegisterComponent } from './modules/auth/components/register/register.component';
import { PasswordRecoveryComponent } from './modules/auth/components/password-recovery/password-recovery.component';
import { PasswordRecoveryFormComponent } from './modules/auth/components/password-recovery-form/password-recovery-form.component';
import { AccountActivationComponent } from './modules/auth/components/account-activation/account-activation.component';
import { UnauthGuard } from './modules/core/guards/unauth.guard';

const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'logowanie', component: LoginComponent, canActivate: [UnauthGuard] },
  { path: 'rejestracja', component: RegisterComponent, canActivate: [UnauthGuard] },
  { path: 'aktywuj/:uid', component: AccountActivationComponent },
  { path: 'odzyskaj-haslo', component: PasswordRecoveryComponent },
  { path: 'odzyskaj-haslo/:uid', component: PasswordRecoveryFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
