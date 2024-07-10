import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { selectAuthError, selectAuthLoading } from 'src/app/modules/auth/store/auth.selectors';
import { RegisterRequest } from 'src/app/modules/core/models/forms.model';
import { FormService } from 'src/app/modules/core/services/form.service';
import { AppState } from 'src/app/store/app.reducer';
import * as AuthActions from '../../../../../auth/store/auth.actions'
import { UserService } from 'src/app/modules/core/services/user.service';
import { UserAllData } from 'src/app/modules/core/models/user.model';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {

  constructor(
    private formService: FormService, 
    private router: Router, 
    private store: Store<AppState>,
    private userService: UserService) {
      this.getDepartments();
    }

  registerForm: FormGroup<RegisterRequest> = this.formService.initRegisterForm();
  notMatchingPasswordsError: string | null = null;
  departments: string[] = [];
  currentUser: UserAllData | null = null;
  errorMsg$: Observable<string | null> = this.store.select(selectAuthError);
  loading$: Observable<boolean> = this.store.select(selectAuthLoading);
  
  get controls(){
    return this.registerForm.controls;
  }

  getErrorMessage(control: FormControl): string{
    return this.formService.getErrorMessage(control);
  }

  onRegister(){
    const {firstname, lastname, email, password, repeatedPassword, department} = this.registerForm.getRawValue();
    if(password !== repeatedPassword){
      this.notMatchingPasswordsError = 'Hasła muszą być takie same'
      return;
    }
    this.store.dispatch(AuthActions.addUser({registerData: {firstname, lastname, email, password, department}}))
  }

  ngOnDestroy(): void {
    this.store.dispatch(AuthActions.clearError());
  }

  getDepartments(): void {
    this.userService.departments().subscribe({
      next: (response: string[]) => {
        this.departments = response;
      },
      error: (err) => {
        console.error('Error fetching departments:', err);
      }
    });
  }
}
