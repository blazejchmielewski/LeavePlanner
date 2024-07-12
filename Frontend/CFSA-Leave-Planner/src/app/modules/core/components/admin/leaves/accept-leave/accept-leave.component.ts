import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DialogData } from '../../users/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-accept-leave',
  templateUrl: './accept-leave.component.html',
  styleUrls: ['./accept-leave.component.css']
})
export class AcceptLeaveComponent {

  constructor(
    public dialogRef: MatDialogRef<AcceptLeaveComponent>,
    private _snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  onNoClick(): void {
    this.dialogRef.close(false);
  }

  onConfirmClick(): void {
    this.dialogRef.close(true);
  }
}
