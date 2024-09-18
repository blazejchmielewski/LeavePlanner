import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { LeaveDataDetailsExtended, LeaveStatus, LeaveType } from '../../../models/leave.model';
import { LeaveService } from '../../../services/leave.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { AcceptLeaveComponent } from './accept-leave/accept-leave.component';
import { RejectLeaveComponent } from './reject-leave/reject-leave.component';

@Component({
  selector: 'app-leaves',
  templateUrl: './leaves.component.html',
  styleUrls: ['./leaves.component.css']
})
export class LeavesComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['lp', 'creator', 'replacing', 'type', 'status', 'from', 'to', 'actions'];
  leaveToShow: LeaveDataDetailsExtended | null = null;
  leaves: LeaveDataDetailsExtended[] = [];
  dataSource!: MatTableDataSource<LeaveDataDetailsExtended>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private router: Router, 
              private leaveService: LeaveService,
              public dialog: MatDialog,
              private changeDetectorRefs: ChangeDetectorRef,
              private _snackBar: MatSnackBar) { }
              
  ngOnInit(): void {
    this.refreshData();
  }

  ngAfterViewInit(): void {
    this.refreshData();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value.trim().toLowerCase();
    this.dataSource.filter = filterValue;

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  translatorStatus(status: string){
    return this.leaveService.translateStatus(status);
  }

  translatorType(type: string){
    return this.leaveService.translateType(type);
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  refreshData(): void {
    this.leaveService.getAll().subscribe({
      next: (leaves) => {
        this.leaves = leaves.filter(l=>l.status !== LeaveStatus.REJECTED_BY_REPLACER).slice();
        this.dataSource = new MatTableDataSource<LeaveDataDetailsExtended>(this.leaves);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        // Ustawienie niestandardowego filtra
        this.dataSource.filterPredicate = (data: LeaveDataDetailsExtended, filter: string) => {
          const translatedType = this.translatorType(data.type).toLowerCase();
          const translatedStatus = this.translatorStatus(data.status).toLowerCase();
          const declaringUser = data.declaringUser.toLowerCase();
          const replacementUser = data.replacementUser.toLowerCase();
          const startDate = (new Date(data.startDate)).toLocaleDateString().toLowerCase();
          const endDate = (new Date(data.endDate)).toLocaleDateString().toLowerCase();

          return translatedType.includes(filter) ||
                 translatedStatus.includes(filter) ||
                 declaringUser.includes(filter) ||
                 replacementUser.includes(filter) ||
                 startDate.includes(filter) ||
                 endDate.includes(filter);
        };
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
      width: '500px',
      data: leave
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'success') {
        this.refreshData();
      }
    });
  }

  openRejectDialog(leave: LeaveDataDetailsExtended) {
    const dialogRef = this.dialog.open(RejectLeaveComponent, {
      width: '500px',
      data: leave
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'success') {
        this.refreshData();
      }
    });
  }

  isDateBeforeToday(date: Date): boolean {
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    
    const givenDate = new Date(date);
    givenDate.setHours(0, 0, 0, 0);
    
    return givenDate < today;
  }

  getDate(): Date {
    return new Date();
  }

  getStatusClass(status: string): string {
    if (status === 'ZAAKCEPTOWANY PRZEZ ZARZĄD') {
      return 'accepted';
    } else if (status === 'ODRZUCONY PRZEZ ZARZĄD') {
      return 'rejected';
    } else {
      return 'pending';
    }
  }
}
