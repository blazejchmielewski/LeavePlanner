import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import * as AuthActions from '../../../auth/store/auth.actions'
import { AppState } from 'src/app/store/app.reducer';
import { Observable } from 'rxjs';
import { User } from '../../models/auth.model';
import { selectAuthUser } from 'src/app/modules/auth/store/auth.selectors';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  user$: Observable<User | null> = this.store.select(selectAuthUser);
  constructor(private store: Store<AppState>) {}
  
  logout() {
    this.store.dispatch(AuthActions.logout());
  }

  isAdmin(role: string) {
    return role === 'ADMIN';
  }

}
