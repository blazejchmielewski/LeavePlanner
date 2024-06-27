import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { switchMap } from 'rxjs';
import { AuthService } from 'src/app/modules/core/services/auth.service';

@Component({
  selector: 'app-account-activation',
  templateUrl: './account-activation.component.html',
  styleUrls: ['./account-activation.component.css']
})
export class AccountActivationComponent implements OnInit{

  errorMessage: string | null = null;

  constructor(
    private route: ActivatedRoute, 
    private authService: AuthService,
    private notifier: NotifierService,
    private router: Router){}
  
  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => this.authService.activateAccount(params.get('uid') as string))
    ).subscribe({
      next: (response) => {
        this.router.navigate(['/logowanie'])
        this.notifier.notify('success', response.message)
        console.log(response)
      }, error: (err)=> {
        this.errorMessage = err;
      }
    })
  }
}
