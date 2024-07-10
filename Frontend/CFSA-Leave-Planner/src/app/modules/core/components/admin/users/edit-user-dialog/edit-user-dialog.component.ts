import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from 'src/app/modules/core/models/auth.model';
import { EditUserRequest } from 'src/app/modules/core/models/forms.model';
import { UserAllData } from 'src/app/modules/core/models/user.model';
import { AuthService } from 'src/app/modules/core/services/auth.service';
import { FormService } from 'src/app/modules/core/services/form.service';
import { UserService } from 'src/app/modules/core/services/user.service';

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent implements OnInit {
  editForm: FormGroup<EditUserRequest> = this.formService.initEditUserForm();
  currentUser: UserAllData | null = null;
  departments: string[] = [];

  constructor(
    private formService: FormService, 
    private authService: AuthService,
    private userService: UserService,
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<EditUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public user: User
  ) {}

  ngOnInit(): void {
    
    this.currentUser = this.userService.getCurrentUser();
    this.getDepartments();
  
    if (this.currentUser) {
      this.editForm.patchValue({
        firstname: this.currentUser.firstname,
        lastname: this.currentUser.lastname,
        email: this.currentUser.email,
        department: this.currentUser.department
      });
    }
  }
  
  getErrorMessage(control: FormControl): string {
    return this.formService.getErrorMessage(control);
  }

  getDepartments(): void {
    this.userService.departments().subscribe({
      next: (response: string[]) => {
        this.departments = response;
      },
      error: (err) => {
        console.error('Error fetching departments:', err);
      }
    });
  }

  get controls(): EditUserRequest {
    return this.editForm.controls;
  }

  onEdit(): void {
    if (this.editForm.valid) {
      const currentUser = this.userService.getCurrentUser();
      if (!currentUser) {
        console.error('Current user not found.');
        return;
      }
      
      const updatedUser = {
        ...currentUser,
        ...this.editForm.value
      };
  
      this.userService.edit(currentUser.id, updatedUser).subscribe({
        next: (response) => {
          console.log('User updated successfully:', response);
          this.dialogRef.close(true);
          window.location.reload();
        },
        error: (err) => {
          console.error('Error updating user:', err);
        }
      });
    }
  }
  
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
}
