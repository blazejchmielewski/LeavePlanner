import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import * as AuthActions from '../../../auth/store/auth.actions'
import { AppState } from 'src/app/store/app.reducer';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private store: Store<AppState>){}

  logout(){
    this.store.dispatch(AuthActions.logout())
  }
}
