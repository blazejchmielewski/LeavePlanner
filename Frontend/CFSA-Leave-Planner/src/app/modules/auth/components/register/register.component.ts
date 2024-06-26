import { Component, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { RegisterRequest } from 'src/app/modules/core/models/forms.model';
import { FormService } from 'src/app/modules/core/services/form.service';
import * as AuthActions from '../../store/auth.actions'
import { AppState } from 'src/app/store/app.reducer';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { selectAuthError, selectAuthLoading } from '../../store/auth.selectors';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnDestroy {

  constructor(private formService: FormService, private router: Router, private store: Store<AppState>) {}

  registerForm: FormGroup<RegisterRequest> = this.formService.initRegisterForm();
  notMatchingPasswordsError: string | null = null;

  errorMsg$: Observable<string | null> = this.store.select(selectAuthError);
  loading$: Observable<boolean> = this.store.select(selectAuthLoading);
  
  get controls(){
    return this.registerForm.controls;
  }

  onSubmit(){
    this.router.navigate(['/home'])
  }

  getErrorMessage(control: FormControl): string{
    return this.formService.getErrorMessage(control);
  }

  onRegister(){
    const {firstname, lastname, email, password, repeatedPassword} = this.registerForm.getRawValue();
    if(password !== repeatedPassword){
      this.notMatchingPasswordsError = 'Hasła muszą być takie same'
      return;
    }
    this.store.dispatch(AuthActions.register({registerData: {firstname, lastname, email, password}}))
  }

  ngOnDestroy(): void {
    this.store.dispatch(AuthActions.clearError());
  }
}