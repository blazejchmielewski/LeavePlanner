import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AppState } from './store/app.reducer';
import * as AuthActions from '../app/modules/auth/store/auth.actions'
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'CFSA-Leave-Planner';

  isLoginPage: boolean = false;
  isRegisterPage: boolean = false;
  isPasswordRecoveryPage: boolean = false;
  isPasswordRecoveryFormPage: boolean = false;

  constructor(private router: Router, private store: Store<AppState>) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.isLoginPage = event.url === '/logowanie';
        this.isRegisterPage = event.url === '/rejestracja';
        this.isPasswordRecoveryPage = event.url === '/odzyskaj-haslo';
        this.isPasswordRecoveryFormPage = event.url.includes('/odzyskaj-haslo/');
      }
    });
  }

  ngOnInit(): void {
    this.store.dispatch(AuthActions.autoLogin());
  }

}
