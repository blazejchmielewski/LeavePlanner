import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import * as AuthActions from '../../../auth/store/auth.actions'
import { AppState } from 'src/app/store/app.reducer';
import { Observable } from 'rxjs';
import { User } from '../../models/auth.model';
import { selectAuthUser } from 'src/app/modules/auth/store/auth.selectors';
import { LeaveService } from '../../services/leave.service';
import { LeaveStatus } from '../../models/leave.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  count: number = 0;
  user$: Observable<User | null> = this.store.select(selectAuthUser);
  constructor(private store: Store<AppState>,
    private leaveService: LeaveService
  ) {}

  ngOnInit(): void {
    this.getCount();
  }
  
  logout() {
    this.store.dispatch(AuthActions.logout());
  }

  isAdmin(role: string) {
    return role === 'ADMIN';
  }

  getCount(){
    this.leaveService.getAllReplacements().subscribe({
      next: (resp)=>{
        this.count = resp.filter(r=>r.status === LeaveStatus.PENDING).length
      }
    })
  }

}
