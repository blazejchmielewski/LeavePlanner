import { Component } from '@angular/core';
import { Route, Router, RouterLink } from '@angular/router';
import { FormService } from '../../core/services/form.service';
import { FormControl, FormGroup } from '@angular/forms';
import { LoginRequest, RegisterRequest } from '../../core/models/forms.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {


  constructor(private formService: FormService, private router: Router){}

  registerForm: FormGroup<RegisterRequest> = this.formService.initRegisterForm();

  get controls(){
    return this.registerForm.controls;
  }

  onSubmit(){
    this.router.navigate(['/home'])
  }

  getErrorMessage(control: FormControl): string{
    return this.formService.getErrorMessage(control);
  }
}
