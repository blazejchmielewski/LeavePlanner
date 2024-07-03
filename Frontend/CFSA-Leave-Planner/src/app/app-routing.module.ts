import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/components/login/login.component';
import { HomeComponent } from './modules/core/components/home/home.component';
import { RegisterComponent } from './modules/auth/components/register/register.component';
import { PasswordRecoveryComponent } from './modules/auth/components/password-recovery/password-recovery.component';
import { PasswordRecoveryFormComponent } from './modules/auth/components/password-recovery-form/password-recovery-form.component';
import { AccountActivationComponent } from './modules/auth/components/account-activation/account-activation.component';
import { UnauthGuard } from './modules/core/guards/unauth.guard';
import { AdminComponent } from './modules/core/components/admin/admin.component';
import { AuthGuard } from './modules/core/guards/auth.guard';
import { CalendarComponent } from './modules/core/components/header/calendar/calendar.component';
import { UsersComponent } from './modules/core/components/admin/users/users.component';

const routes: Routes = [
  { path: '', component: AdminComponent},
  { path: 'home', component: HomeComponent},
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard]},
  { path: 'logowanie', component: LoginComponent, canActivate: [UnauthGuard] },
  { path: 'rejestracja', component: RegisterComponent, canActivate: [UnauthGuard] },
  { path: 'aktywuj/:uid', component: AccountActivationComponent },
  { path: 'odzyskaj-haslo', component: PasswordRecoveryComponent },
  { path: 'odzyskaj-haslo/:uid', component: PasswordRecoveryFormComponent },
  { path: 'calendar', component: CalendarComponent},
  { path: 'users', component: UsersComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
