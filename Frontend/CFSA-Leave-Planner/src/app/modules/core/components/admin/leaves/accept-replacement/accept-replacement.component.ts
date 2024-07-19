import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { LeaveDataDetails, LeaveDataDetailsExtended } from 'src/app/modules/core/models/leave.model';
import { LeaveService } from 'src/app/modules/core/services/leave.service';
import { AcceptLeaveComponent } from '../accept-leave/accept-leave.component';
import { AcceptReplacementDetailsComponent } from './accept-replacement-details/accept-replacement-details.component';
import { RejectReplacementDetailsComponent } from './reject-replacement-details/reject-replacement-details.component';

@Component({
  selector: 'app-accept-replacement',
  templateUrl: './accept-replacement.component.html',
  styleUrls: ['./accept-replacement.component.css']
})
export class AcceptReplacementComponent {

  replacements: LeaveDataDetails[] = [];
  displayedColumns: string[] = ['lp', 'replacing', 'creator', 'type', 'status', 'from', 'to', 'actions'];
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
    this.leaveService.getAllReplacements().subscribe({
      next: (leaves) => {
        this.leaves = leaves.slice();
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

  getAllReplacements(){
    this.leaveService.getAllReplacements().subscribe({
      next: (resp)=>{
        if(resp){
          this.replacements = resp;
        }
      }, error: (err) => {
        console.log(err);
      }
    })
  }

  refresh() {
    this.leaveService.getAllUserLeaves().subscribe((res) => {
      this.changeDetectorRefs.detectChanges();
    });
  }

  openAcceptDialog(leave: LeaveDataDetailsExtended) {
    const dialogRef = this.dialog.open(AcceptReplacementDetailsComponent, {
      width: '1000px',
      data: leave
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'success') {
        this.refresh();
      }
    });
  }

  openRejectDialog(leave: LeaveDataDetailsExtended) {
    const dialogRef = this.dialog.open(RejectReplacementDetailsComponent, {
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

