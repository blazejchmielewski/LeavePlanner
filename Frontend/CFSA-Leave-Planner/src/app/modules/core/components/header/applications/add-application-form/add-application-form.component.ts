import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { LeaveRequest } from 'src/app/modules/core/models/forms.model';
import { LeaveData, LeaveType, UsersToSwitch } from 'src/app/modules/core/models/leave.model';
import { FormService } from 'src/app/modules/core/services/form.service';
import { LeaveService } from 'src/app/modules/core/services/leave.service';

@Component({
  selector: 'app-add-application-form',
  templateUrl: './add-application-form.component.html',
  styleUrls: ['./add-application-form.component.css']
})

export class AddApplicationFormComponent {

  addApplicationForm: FormGroup<LeaveRequest> = this.formService.initLeaveFrom();
  usersToSwitch: UsersToSwitch[] = [];
  leaveTypes = Object.values(LeaveType);

  constructor(private formService: FormService, private leaveService: LeaveService){
    this.getUsersToSwitch();
  }
  
  onAdd(){
    if(!this.addApplicationForm.invalid){
      const body: LeaveData = {
        startDate: this.addApplicationForm.value.startDate ?? new Date(),
        endDate: this.addApplicationForm.value.endDate ?? new Date(),
        type: this.addApplicationForm.value.type ?? '',
        userUuid: this.addApplicationForm.value.userUuid ?? ''
      };
      this.leaveService.addLeave(body).subscribe({
        next: (resp) => {
          console.log(resp)
        
        }, error: (err) => console.log(err)
      })
    }  
  }

  getUsersToSwitch(){
    if(this.leaveService.getUsersToSwitch() != null){
      this.leaveService.getUsersToSwitch().subscribe({
        next: (users) => {
          this.usersToSwitch = users;
        }
      })
    }
  }

  get controls(){
    return this.addApplicationForm.controls;
  }

  getErrorMessage(control: FormControl): string{
    return this.formService.getErrorMessage(control);
  }

  translateLeaveType(type: LeaveType): string {
    switch (type) {
      case LeaveType.ANNUAL_LEAVE:
        return 'Urlop wypoczynkowy';
      case LeaveType.SICK_LEAVE:
        return 'Urlop zdrowotny';
      case LeaveType.MATERNITY_LEAVE:
        return 'Urlop macierzyński';
      case LeaveType.PATERNITY_LEAVE:
        return 'Urlop ojcowski';
      case LeaveType.UNPAID_LEAVE:
        return 'Urlop bezpłatny';
      case LeaveType.OTHER:
        return 'Inny';
      default:
        return '';
    }
  }
  
}
