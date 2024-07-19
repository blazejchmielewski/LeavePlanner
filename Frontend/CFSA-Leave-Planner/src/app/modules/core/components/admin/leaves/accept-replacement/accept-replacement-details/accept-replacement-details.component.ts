import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LeaveDataDetailsExtended, UuidObject } from 'src/app/modules/core/models/leave.model';
import { LeaveService } from 'src/app/modules/core/services/leave.service';

@Component({
  selector: 'app-accept-replacement-details',
  templateUrl: './accept-replacement-details.component.html',
  styleUrls: ['./accept-replacement-details.component.css']
})
export class AcceptReplacementDetailsComponent implements OnInit{

  leaveData!: LeaveDataDetailsExtended;
  uuid: UuidObject = { uuid: '' };
  constructor(
    public dialogRef: MatDialogRef<AcceptReplacementDetailsComponent>,
    private _snackBar: MatSnackBar,
    private router: Router,
    private leaveService: LeaveService,
    @Inject(MAT_DIALOG_DATA) public data: LeaveDataDetailsExtended
  ) {}

  ngOnInit(): void {
    if(this.data){
      this.leaveData = this.data;
    }
  }

  onNoClick(){
    this.dialogRef.close();
  }

  onConfirmClick(): void{
    if(this.data && this.data.leaveUuid) {
      this.uuid.uuid = this.data.leaveUuid;
      this.leaveService.acceptReplacement(this.uuid).subscribe({
        next: (resp) => {
          console.log(resp);
          this._snackBar.open('Zastępstwo zaakceptowano pomyślnie', 'Zamknij', {
            duration: 2000,
          });
          this.dialogRef.close('success');
          this.router.navigate(['/accept-replecament']);
        }, 
        error: (err) => {
          console.log(err);
          this._snackBar.open('Nie udało się zaakceptować zastępstwa', 'Zamknij', {
            duration: 2000,
          });
        }
      });
    }
  }
}
