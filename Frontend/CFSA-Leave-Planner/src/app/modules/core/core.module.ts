import { NgModule } from '@angular/core';
import { HeaderComponent } from './components/header/header.component';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { SharedModule } from '../shared/shared.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'
import { ErrorHandlingInterceptor } from './interceptors/error-handling.interceptor';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { SpinnerInterceptor } from './interceptors/spinner.interceptor';
import { AdminComponent } from './components/admin/admin.component';
import { CalendarComponent } from './components/header/calendar/calendar.component';
import { UsersComponent } from './components/admin/users/users.component';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { ConfirmDialogComponent } from './components/admin/users/confirm-dialog/confirm-dialog.component';
import { EditUserDialogComponent } from './components/admin/users/edit-user-dialog/edit-user-dialog.component';

@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent,
    SpinnerComponent,
    AdminComponent,
    CalendarComponent,
    UsersComponent,
    ConfirmDialogComponent,
    EditUserDialogComponent
  ],
  imports: [
    HttpClientModule,
    SharedModule,
    RouterLink,
    RouterLinkActive,
  ],
  exports:[
    HeaderComponent,
    HomeComponent,
    SpinnerComponent,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlingInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: SpinnerInterceptor,
      multi: true
    },
      {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
      }
    ],
})
export class CoreModule { }
