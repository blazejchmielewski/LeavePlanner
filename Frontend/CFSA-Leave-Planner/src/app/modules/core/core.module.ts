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
import { FormsModule } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { CustomDateAdapter, MY_DATE_FORMATS } from './adapters/CustomDateAdapter';
import { AddUserComponent } from './components/admin/users/add-user/add-user.component';
import { ApplicationsComponent } from './components/header/applications/applications.component';
import { AddApplicationFormComponent } from './components/header/applications/add-application-form/add-application-form.component';
import { ApplicationDetailsComponent } from './components/header/applications/application-details/application-details.component';

@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent,
    SpinnerComponent,
    AdminComponent,
    CalendarComponent,
    UsersComponent,
    ConfirmDialogComponent,
    EditUserDialogComponent,
    AddUserComponent,
    ApplicationsComponent,
    AddApplicationFormComponent,
    ApplicationDetailsComponent,
  ],
  imports: [
    HttpClientModule,
    SharedModule,
    FormsModule,
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
      },
    { provide: DateAdapter, useClass: CustomDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS },
    { provide: MAT_DATE_LOCALE, useValue: 'pl' }
    ],
})
export class CoreModule { }
