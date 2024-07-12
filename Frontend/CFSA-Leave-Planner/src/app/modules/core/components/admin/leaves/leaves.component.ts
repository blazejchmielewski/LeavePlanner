import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { LeaveDataDetails, LeaveDataDetailsExtended, LeaveType } from '../../../models/leave.model';
import { LeaveService } from '../../../services/leave.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AcceptLeaveComponent } from './accept-leave/accept-leave.component';

@Component({
  selector: 'app-leaves',
  templateUrl: './leaves.component.html',
  styleUrls: ['./leaves.component.css']
})
export class LeavesComponent {

  displayedColumns: string[] = ['lp', 'creator', 'replacing','type', 'from', 'to', 'actions'];
  leaveToShow: LeaveDataDetailsExtended | null = null;


  constructor(private router: Router, 
    private leaveService: LeaveService,
    public dialog: MatDialog,
    private changeDetectorRefs: ChangeDetectorRef,
    private _snackBar: MatSnackBar){}
  
  ngAfterViewInit(): void {
    this.refreshData();
  }

  leaves: LeaveDataDetails[] = [];

  dataSource !: MatTableDataSource<LeaveDataDetails>
  @ViewChild (MatPaginator) paginator !: MatPaginator
  @ViewChild (MatSort) sort !: MatSort;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
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

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  refreshData(): void {
    this.leaveService.getAllUserLeaves().subscribe({
      next: (leaves) => {
        this.leaves = leaves.slice();
        this.dataSource = new MatTableDataSource<LeaveDataDetails>(this.leaves);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        console.log(err);
      }
    });
    }

    refresh() {
      this.leaveService.getAllUserLeaves().subscribe((res) => {
        this.changeDetectorRefs.detectChanges();
      });
    }

    openAcceptDialog(leave: LeaveDataDetailsExtended) {
      const dialogRef = this.dialog.open(AcceptLeaveComponent, {
        width: '400px',
      });
  
      dialogRef.afterClosed().subscribe(result => {
        if (result === 'success') {
          this.refresh();
        }
      });
    } 
}
