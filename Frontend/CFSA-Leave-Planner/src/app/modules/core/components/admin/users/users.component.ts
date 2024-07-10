import { AfterViewInit, ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { EditUserDialogComponent } from './edit-user-dialog/edit-user-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { UserAllData } from '../../../models/user.model';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements AfterViewInit {

  displayedColumns: string[] = ['lp', 'firstname', 'lastname', 'email', 'department' , 'actions'];
  
  currentUser!: UserAllData; 
  users: UserAllData[] = [];
  userToDelete: UserAllData | null = null;
  
  dataSource !: MatTableDataSource<UserAllData>
  @ViewChild (MatPaginator) paginator !: MatPaginator
  @ViewChild (MatSort) sort !: MatSort;

  constructor(private userService: UserService,
    public dialog: MatDialog,
    private changeDetectorRefs: ChangeDetectorRef,
    private router: Router,
    private _snackBar: MatSnackBar) {}

  ngAfterViewInit(): void {
    this.refreshData();
  }

  refreshData(): void {
  this.userService.getAllUsers().subscribe({
    next: (users) => {
      this.users = users.slice();
      this.dataSource = new MatTableDataSource<UserAllData>(this.users);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    },
    error: (err) => {
      console.log(err);
    }
  });
}
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openEditUserDialog(user: UserAllData) {
    console.log("robie")
    this.userService.setCurrentUser(user);
    const dialogRef = this.dialog.open(EditUserDialogComponent, {
      width: '700px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'success') {
        this.refresh();
      }
    });
  } 

  refresh() {
    this.userService.getAllUsers().subscribe((res) => {
      this.changeDetectorRefs.detectChanges();
    });
  }

  

  confirmDeactivateUser(user: UserAllData): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: { name: `${user.firstname} ${user.lastname}` }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deactivateUser(user);
      }
    });
  }

  deactivateUser(user: UserAllData): void {
    this.userService.expireUser(user.id).subscribe({
      next: (response) => {
        console.log(response);
        this.users = this.users.filter(u => u.id !== user.id);
        this.dataSource.data = this.users;
        this.openSnackBar('Zablokowano użytkownika', 'Zamknij');
      },
      error: (err) => {
        console.error('Wystąpił błąd podczas wygaszania użytkownika:', err);
      }
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  navigateToRegister(){
    this.router.navigate(['/add-user'])
  }
}
