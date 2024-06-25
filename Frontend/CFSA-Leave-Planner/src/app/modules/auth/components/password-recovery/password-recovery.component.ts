import { Component } from '@angular/core';
import { FormService } from '../../../core/services/form.service';
import { FormControl, FormGroup } from '@angular/forms';
import { PasswordRecoveryRequest, ToGetPasswordRecoveryRequest } from '../../../core/models/forms.model';

@Component({
  selector: 'app-password-recovery',
  templateUrl: './password-recovery.component.html',
  styleUrls: ['./password-recovery.component.css']
})
export class PasswordRecoveryComponent {

  passwordRecoveryForm: FormGroup<ToGetPasswordRecoveryRequest> = this.formService.initToGetPasswordRecoveryForm();

  constructor(private formService: FormService){}

  getErrorMessage(email: FormControl<string>): string{
    return this.formService.getErrorMessage(email);
  }
}
