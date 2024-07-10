import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';
import { LeaveService } from '../../../services/leave.service';

@Component({
  selector: 'app-applications',
  templateUrl: './applications.component.html',
  styleUrls: ['./applications.component.css']
})
export class ApplicationsComponent {

  constructor(private router: Router){}

  navigateToForm(){
    this.router.navigate(['/add-application-form'])
  }

}
