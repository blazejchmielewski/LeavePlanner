import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { FormService } from '../../../core/services/form.service';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginRequest } from '../../../core/models/forms.model';
import * as AuthActions from '../../store/auth.actions'
import { Store } from '@ngrx/store';
import { AppState } from 'src/app/store/app.reducer';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnDestroy{

  constructor(private formService: FormService, private router: Router, private store: Store<AppState>){}
  

  loginForm: FormGroup<LoginRequest> = this.formService.initLoginForm();

  get controls(){
    return this.loginForm.controls;
  }

  onSubmit(){
    this.router.navigate(['/home'])
  }

  getErrorMessage(control: FormControl): string{
    return this.formService.getErrorMessage(control);
  }

  onLogin(){
    this.store.dispatch(
      AuthActions.login({loginData: this.loginForm.getRawValue()})
    );
  }

  ngOnDestroy(): void {
    this.store.dispatch(AuthActions.clearError());
  }
}
