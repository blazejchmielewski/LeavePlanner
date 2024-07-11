import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { LeaveDataDetailsExtended, LeaveStatus } from 'src/app/modules/core/models/leave.model';
import { LeaveService } from 'src/app/modules/core/services/leave.service';


@Component({
  selector: 'app-application-details',
  templateUrl: './application-details.component.html',
  styleUrls: ['./application-details.component.css']
})
export class ApplicationDetailsComponent implements OnInit{

  uid = '';
  leaveToShow: LeaveDataDetailsExtended | null = null;
  constructor(
  private leaveService: LeaveService, private router: Router, private route: ActivatedRoute,){}
  
  @Input() leaveStatus: LeaveStatus = LeaveStatus.PENDING;
  
  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => this.leaveService.getLeaveByUuid(params.get('uuid') as string))
    ).subscribe({
      next: (resp) => {
        console.log(resp);
        this.leaveToShow = resp
      }, error: (err) => {
        console.log(err)
      }
    })
  }

  shouldDisplayStep(): boolean {
    switch (this.leaveStatus) {
      case LeaveStatus.PENDING:
      case LeaveStatus.APPROVED:
      case LeaveStatus.REJECTED:
      case LeaveStatus.CANCELLED:
        return true;
      default:
        return false;
    }
  }
}
