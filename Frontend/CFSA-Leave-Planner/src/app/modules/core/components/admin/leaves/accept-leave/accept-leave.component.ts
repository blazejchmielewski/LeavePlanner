import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LeaveService } from 'src/app/modules/core/services/leave.service';
import { LeaveDataDetailsExtended, UuidObject } from 'src/app/modules/core/models/leave.model';

@Component({
  selector: 'app-accept-leave',
  templateUrl: './accept-leave.component.html',
  styleUrls: ['./accept-leave.component.css']
})
export class AcceptLeaveComponent {

  uid: UuidObject = { uuid: '' };

  constructor(
    public dialogRef: MatDialogRef<AcceptLeaveComponent>,
    private _snackBar: MatSnackBar,
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
      this.leaveService.acceptLeave(this.uid).subscribe({
        next: (resp) => {
          console.log(resp);
          this._snackBar.open('Urlop zaakceptowany pomyślnie', 'Zamknij', {
            duration: 2000,
          });
          this.dialogRef.close('success');
        }, 
        error: (err) => {
          console.log(err);
          this._snackBar.open('Nie udało się zaakceptować urlopu', 'Zamknij', {
            duration: 2000,
          });
        }
      });
    }
  }
}
