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

  translatorStatus(status: string){
    return this.leaveService.translateStatus(status);
  }

  translatorType(type: string){
    return this.leaveService.translateType(type);
  }

  getStageClass(stage: number): string {
    if (!this.leaveToShow) {
      return '';
    }
    const status = this.leaveToShow;

    if(
      (stage === 1) ||
      (this.leaveToShow.status === (LeaveStatus.APPROVED_BY_REPLACER) && this.leaveToShow.settledByReplacerDate && stage <= 2) ||
      this.leaveToShow.status === (LeaveStatus.REJECTED_BY_ACCEPTOR) && (this.leaveToShow.settledByReplacerDate && this.leaveToShow.settledByAcceptorDate) && stage == 2 ||
      (this.leaveToShow.status === (LeaveStatus.APPROVED_BY_ACCEPTOR) && (this.leaveToShow.settledByReplacerDate && this.leaveToShow.settledByAcceptorDate) && stage <= 3)
    ){
      return 'active'
    } else if(
      (this.leaveToShow.status === (LeaveStatus.REJECTED_BY_REPLACER) && this.leaveToShow.settledByReplacerDate && stage <= 2) ||
      (this.leaveToShow.status === (LeaveStatus.REJECTED_BY_ACCEPTOR) && (this.leaveToShow.settledByReplacerDate && this.leaveToShow.settledByAcceptorDate) && stage == 3 )
    ){
      return 'rejected';
    }
    return 'inactive';
  }
}
