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
import { AddUserComponent } from './modules/core/components/admin/users/add-user/add-user.component';
import { ApplicationsComponent } from './modules/core/components/header/applications/applications.component';
import { AddApplicationFormComponent } from './modules/core/components/header/applications/add-application-form/add-application-form.component';
import { ApplicationDetailsComponent } from './modules/core/components/header/applications/application-details/application-details.component';
import { LeavesComponent } from './modules/core/components/admin/leaves/leaves.component';
import { UserDetailsComponent } from './modules/core/components/user/user-details/user-details.component';
import { AcceptReplacementComponent } from './modules/core/components/admin/leaves/accept-replacement/accept-replacement.component';
import { CalendarPanelComponent } from './modules/core/components/admin/calendar-panel/calendar-panel.component';
import { YearComponent } from './modules/core/components/admin/calendar-panel/year/year.component';

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
  { path: 'add-user', component: AddUserComponent},
  { path: 'application', component: ApplicationsComponent},
  { path: 'add-application-form', component: AddApplicationFormComponent},
  { path: 'application-details/:uuid', component: ApplicationDetailsComponent},
  { path: 'leaves', component: LeavesComponent},
  { path: 'user-details', component: UserDetailsComponent},
  { path: 'accept-replecament', component: AcceptReplacementComponent},
  { path: 'calendar-panel', component: CalendarPanelComponent},
  { path: 'year/:id', component: YearComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
