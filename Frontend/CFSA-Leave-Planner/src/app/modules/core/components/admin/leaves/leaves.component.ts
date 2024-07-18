import { AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { LeaveDataDetailsExtended, LeaveType } from '../../../models/leave.model';
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

  translateLeaveType(type: string): string {
    switch (type) {
      case 'ANNUAL_LEAVE': return 'Urlop wypoczynkowy';
      case 'SICK_LEAVE': return 'Urlop zdrowotny';
      case 'MATERNITY_LEAVE': return 'Urlop macierzyński';
      case 'PATERNITY_LEAVE':return 'Urlop ojcowski';
      case 'UNPAID_LEAVE':return 'Urlop bezpłatny';
      case 'OTHER':return 'Inny';
      default:return '';
    }
  }

  translateStatus(status: string): string {
    switch (status) {
      case 'PENDING': return 'OCZEKUJĄCY';
      case 'APPROVED': return 'ZAAKCEPTOWANY';
      case 'IN_PROGRESS': return 'ROZPATRYWANY';
      case 'REJECTED': return 'ODRZUCONY';
      case 'CANCELLED': return 'ANULOWANY';
      default: return '';
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  refreshData(): void {
    this.leaveService.getAll().subscribe({
      next: (leaves) => {
        this.leaves = leaves.slice();
        this.dataSource = new MatTableDataSource<LeaveDataDetailsExtended>(this.leaves);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        // Ustawienie niestandardowego filtra
        this.dataSource.filterPredicate = (data: LeaveDataDetailsExtended, filter: string) => {
          const translatedType = this.translateLeaveType(data.type).toLowerCase();
          const translatedStatus = this.translateStatus(data.status).toLowerCase();
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
        this.refresh();
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
        this.refresh();
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
}
