import { Component, OnInit } from '@angular/core';
import { FormService } from '../../../core/services/form.service';
import { FormControl, FormGroup } from '@angular/forms';
import { PasswordRecoveryRequest } from '../../../core/models/forms.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-password-recovery-form',
  templateUrl: './password-recovery-form.component.html',
  styleUrls: ['./password-recovery-form.component.css']
})
export class PasswordRecoveryFormComponent implements OnInit{

  passwordRecoveryForm: FormGroup<PasswordRecoveryRequest> = this.formService.initPasswordRecoveryForm();

  constructor(private formService: FormService, private route: ActivatedRoute){}

  get controls(){
    return this.passwordRecoveryForm.controls;
  }

  getErrorMessage(email: FormControl<string>): string{
    return this.formService.getErrorMessage(email);
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe({
      next: (param) => {
        console.log(param.get('uid'));
      }
    })
  }
}
