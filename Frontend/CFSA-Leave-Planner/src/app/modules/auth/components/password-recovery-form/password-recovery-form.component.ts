import { Component, OnInit } from '@angular/core';
import { FormService } from '../../../core/services/form.service';
import { FormControl, FormGroup } from '@angular/forms';
import { PasswordRecoveryRequest } from '../../../core/models/forms.model';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { AuthService } from 'src/app/modules/core/services/auth.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-password-recovery-form',
  templateUrl: './password-recovery-form.component.html',
  styleUrls: ['./password-recovery-form.component.css']
})
export class PasswordRecoveryFormComponent implements OnInit{

  passwordRecoveryForm: FormGroup<PasswordRecoveryRequest> = this.formService.initPasswordRecoveryForm();
  uid = '';
  errorMessage: null | string = null;

  constructor(private formService: FormService, 
    private route: ActivatedRoute,
    private authService: AuthService,
    private notifier: NotifierService,
    private router: Router){}

  get controls(){
    return this.passwordRecoveryForm.controls;
  }

  getErrorMessage(email: FormControl<string>): string{
    return this.formService.getErrorMessage(email);
  }

  onPasswordChange(){
    const {password, repeatedPassword} = this.passwordRecoveryForm.getRawValue();
    this.authService.changePassword({password, uid: this.uid}).subscribe({
      next: ()=> {
        this.router.navigate(['/logowanie'])
        this.notifier.notify('success', 'Poprawnie zmieniono hasło')
      }, error: (err)=> {
        this.errorMessage = err;
      }
    })
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe({
      next: (param) => {
        this.uid = param.get('uid') as string;
        console.log(param.get('uid'));
      }
    })
  }
}
