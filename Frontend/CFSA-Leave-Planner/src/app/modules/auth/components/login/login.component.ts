import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormService } from '../../../core/services/form.service';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginRequest } from '../../../core/models/forms.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private formService: FormService, private router: Router){}

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
}
