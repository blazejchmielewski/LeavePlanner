import { NgModule } from '@angular/core';
import { HeaderComponent } from './header/header.component';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    HeaderComponent,
    HomeComponent
  ],
  imports: [
    SharedModule,
    RouterLink,
    RouterLinkActive
  ],
  exports:[
    HeaderComponent,
    HomeComponent
  ]
})
export class CoreModule { }
