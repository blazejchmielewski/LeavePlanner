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

  translatorStatus(status: string): string{
    switch(status){
      case 'PENDING': return 'OCZEKUJĄCY';
      case 'APPROVED': return 'ZAAKCEPTOWANY';
      case 'IN_PROGRESS': return 'ROZPATRYWANY';
      case 'REJECTED': return 'ODRZUCONY';
      case 'CANCELLED': return 'ANULOWANY';
    }
    return '';
  }

  translatorType(type: string): string{
    switch(type){
      case 'SICK_LEAVE': return 'ZDROWOTNY';
      case 'ANNUAL_LEAVE': return 'WYPOCZYNKOWY';
      case 'MATERNITY_LEAVE': return 'MACIERZYŃSKI';
      case 'PATERNITY_LEAVE': return 'OJCOWSKI';
      case 'UNPAID_LEAVE': return 'NIEPŁATNY';
      case 'OTHER': return 'INNY';
    }
    return ''
  }

  getStageClass(stage: number): string {
    if (!this.leaveToShow) {
      return '';
    }
    const status = this.leaveToShow.status;
    if ((status === LeaveStatus.APPROVED && stage <= 3) || 
        (status === LeaveStatus.IN_PROGRESS && stage <= 2) || 
        (status === LeaveStatus.PENDING && stage === 1)) {
      return 'active';
    }
    return 'inactive';
  }
}
