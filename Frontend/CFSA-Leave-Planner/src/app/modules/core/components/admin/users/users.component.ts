import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UserToTableData } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements AfterViewInit {

  displayedColumns: string[] = ['lp', 'firstname', 'lastname', 'email', 'department', 'buttons'];
  
  currentUser!: UserToTableData; 
  users: UserToTableData[] = [];

  dataSource !: MatTableDataSource<UserToTableData>
  @ViewChild (MatPaginator) paginator !: MatPaginator
  @ViewChild (MatSort) sort !: MatSort;

  constructor(private userService: UserService, private router: Router){}

  ngAfterViewInit(): void {
    this.refreshData();
  }

  refreshData(): void {
  this.userService.getAllUsers().subscribe({
    next: (users) => {
      this.users = users; // users jest już tablicą, nie używamy slice() ani przypisania pojedynczego obiektu
      this.dataSource = new MatTableDataSource<UserToTableData>(this.users);
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

}
