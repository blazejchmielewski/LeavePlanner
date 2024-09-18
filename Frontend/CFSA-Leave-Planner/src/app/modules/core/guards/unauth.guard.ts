import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, catchError, map, of, take } from 'rxjs';
import { AuthService } from '../services/auth.service';
import * as AuthActions from '../../auth/store/auth.actions'
import { Store } from '@ngrx/store';

@Injectable({
  providedIn: 'root'
})
export class UnauthGuard implements CanActivate {
  
  constructor(private authService: AuthService, private router: Router, private store: Store){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean 
    | UrlTree> 
    | Promise<boolean 
    | UrlTree> 
    | boolean 
    | UrlTree {
    return this.authService.isLoggedIn().pipe(
      take(1),
      map((resp)=>{
        const isLoggedIn = resp.message;
        this.logout();
        if(isLoggedIn){
          this.router.navigate(['/home']);
          return false;
        }
        return true;
      }),
      catchError((err)=>{
        return of(true);
      })
    )
  }

  private logout() {
    this.store.dispatch(AuthActions.logout());
  }
}
