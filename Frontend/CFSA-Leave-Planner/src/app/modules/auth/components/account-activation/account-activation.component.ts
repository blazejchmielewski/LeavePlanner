import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { AuthService } from 'src/app/modules/core/services/auth.service';

@Component({
  selector: 'app-account-activation',
  templateUrl: './account-activation.component.html',
  styleUrls: ['./account-activation.component.css']
})
export class AccountActivationComponent implements OnInit{

  constructor(private route: ActivatedRoute, private authService: AuthService){}
  
  ngOnInit(): void {
    this.route.paramMap.subscribe({
      next: (param) => {
        console.log(param.get('uid'));
      }
    });

    this.route.paramMap.pipe(
      switchMap(params => this.authService.activateAccount(params.get('uid') as string))
    )
  }

  
}
