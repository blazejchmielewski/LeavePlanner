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

@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent,
    SpinnerComponent,
    AdminComponent
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
    }
  ]
})
export class CoreModule { }
