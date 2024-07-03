import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from '@ngrx/effects'
import { AuthService } from "../../core/services/auth.service";
import * as AuthActions from './auth.actions' 
import { EMPTY, catchError, map, of, switchMap } from "rxjs";
import { Router } from "@angular/router";
import { NotifierService } from "angular-notifier";


@Injectable()
export class AuthEffects {

    login$ = createEffect(()=>
    this.actions$.pipe(
        ofType(AuthActions.login),
        switchMap(action =>{
            return this.authService.login(action.loginData).pipe(
                map((user) => {
                    this.router.navigate(['/']);
                    this.notifierService.notify('success', 'Zalogowano pomyślnie');
                    return AuthActions.loginSuccess({user: {...user}})
                }),
                catchError((err) => {
                    return of(AuthActions.loginFailure({error: err}))}
                ));
        })
    ))

    autoLogin$ = createEffect(()=>
        this.actions$.pipe(
            ofType(AuthActions.autoLogin),
            switchMap(() =>{
                return this.authService.autoLogin().pipe(
                    map((user) => {
                        return AuthActions.autoLoginSuccess({user: {...user}})
                    }),
                    catchError((err)=> of(AuthActions.autoLoginFailure()))
                    );
            })
        )
    )

    register$ = createEffect(()=>
        this.actions$.pipe(
            ofType(AuthActions.register),
            switchMap(action =>{
                return this.authService.register(action.registerData).pipe(
                    map(() => {
                        this.router.navigate(['/logowanie']);
                        this.notifierService.notify('success', 'Na adres e-mail została wysłana wiadomość z linkiem aktywacyjnym')
                        return AuthActions.registerSuccess();
                    }),
                catchError((err) => {
                    return of(AuthActions.registerFailure({error: err}))}
                )
                );
            })
        ));
        

        logout$ = createEffect(()=>
            this.actions$.pipe(
                ofType(AuthActions.logout),
                switchMap(() => {
                    return this.authService.logout().pipe(
                        map((user) => {
                            this.router.navigate(['/logowanie']);
                            this.notifierService.notify('success', 'Wylogowano się');
                            return AuthActions.logoutSuccess();
                        }),
                        catchError((err) => {
                            this.notifierService.notify('warning', err)
                            return of(AuthActions.logoutFailure())}
                        ));
                })
            ))

    constructor(private actions$: Actions, 
                private authService: AuthService,
                private router: Router,
                private notifierService: NotifierService){}


}