import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { RegisterRequest } from 'src/app/modules/core/models/forms.model';
import { FormService } from 'src/app/modules/core/services/form.service';


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
