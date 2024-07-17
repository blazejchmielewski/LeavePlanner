import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LeaveDataDetailsExtended, UuidObject } from 'src/app/modules/core/models/leave.model';
import { LeaveService } from 'src/app/modules/core/services/leave.service';
import { AcceptLeaveComponent } from '../accept-leave/accept-leave.component';

@Component({
  selector: 'app-reject-leave',
  templateUrl: './reject-leave.component.html',
  styleUrls: ['./reject-leave.component.css']
})
export class RejectLeaveComponent {
  uid: UuidObject = { uuid: '' };

  constructor(
    public dialogRef: MatDialogRef<AcceptLeaveComponent>,
    private _snackBar: MatSnackBar,
    private router: Router,
    private leaveService: LeaveService,
    @Inject(MAT_DIALOG_DATA) public data: LeaveDataDetailsExtended
  ) {}

  onNoClick(): void {
    this.data.leaveUuid
    this.dialogRef.close(false);
  }

  onConfirmClick(): void {
    if(this.data && this.data.leaveUuid) {
      this.uid.uuid = this.data.leaveUuid;
      this.leaveService.rejectLeave(this.uid).subscribe({
        next: (resp) => {
          console.log(resp);
          this._snackBar.open('Odrzucono urlop pomyślnie', 'Zamknij', {
            duration: 2000,
          });
          this.dialogRef.close('success');
          this.router.navigate(['/leaves']);
        }, 
        error: (err) => {
          console.log(err);
          this._snackBar.open('Nie udało się odrzucić urlopu', 'Zamknij', {
            duration: 2000,
          });
        }
      });
    }
  }
}