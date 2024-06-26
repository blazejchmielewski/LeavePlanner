import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'CFSA-Leave-Planner';

  isLoginPage: boolean = false;
  isRegisterPage: boolean = false;
  isPasswordRecoveryPage: boolean = false;
  isPasswordRecoveryFormPage: boolean = false;

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoginPage = event.url === '/logowanie';
        this.isRegisterPage = event.url === '/rejestracja';
        this.isPasswordRecoveryPage = event.url === '/odzyskaj-haslo';
        this.isPasswordRecoveryFormPage = event.url.includes('/odzyskaj-haslo/');
      }
    });
  }

}
