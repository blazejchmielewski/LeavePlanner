import { AfterViewInit, ChangeDetectorRef, Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LeaveService } from '../../../services/leave.service';
import { LeaveDataDetails, LeaveDataDetailsExtended, LeaveType } from '../../../models/leave.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApplicationDetailsComponent } from './application-details/application-details.component';

@Component({
  selector: 'app-applications',
  templateUrl: './applications.component.html',
  styleUrls: ['./applications.component.css']
})
export class ApplicationsComponent implements AfterViewInit{

  displayedColumns: string[] = ['lp', 'type', 'from', 'to', 'actions'];
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
  
  navigateToForm(){
    this.router.navigate(['/add-application-form'])
  }

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

    navigateToDetails(uuid: string) {
      console.log(uuid);
      this.router.navigate([`/application-details/${uuid}`]);
    }
}