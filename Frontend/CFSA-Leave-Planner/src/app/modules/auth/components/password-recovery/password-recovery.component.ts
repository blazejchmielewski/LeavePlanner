import { Component } from '@angular/core';
import { FormService } from '../../../core/services/form.service';
import { FormControl, FormGroup } from '@angular/forms';
import { PasswordRecoveryRequest, ToGetPasswordRecoveryRequest } from '../../../core/models/forms.model';
import { AuthService } from 'src/app/modules/core/services/auth.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-password-recovery',
  templateUrl: './password-recovery.component.html',
  styleUrls: ['./password-recovery.component.css']
})
export class PasswordRecoveryComponent {

  passwordRecoveryForm: FormGroup<ToGetPasswordRecoveryRequest> = this.formService.initToGetPasswordRecoveryForm();
  errorMessage: string | null = null;

  constructor(private formService: FormService, private authService: AuthService, private notifierService: NotifierService){}

  getErrorMessage(email: FormControl<string>): string{
    return this.formService.getErrorMessage(email);
  }

  onPasswordRecovery() {
    this.authService.toResetPassword(this.passwordRecoveryForm.getRawValue()).subscribe({
      next: ()=>{
        this.notifierService.notify('success', 'Na maila został podana wiadomość z resetem hasła')
      }, error: (err) => {
        console.log(err);
      }
    })
  }

}
