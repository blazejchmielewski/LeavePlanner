import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DayoffService } from '../../../services/dayoff.service';
import { YearWithHolyCount } from '../../../models/leave.model';

@Component({
  selector: 'app-calendar-panel',
  templateUrl: './calendar-panel.component.html',
  styleUrls: ['./calendar-panel.component.css']
})
export class CalendarPanelComponent implements OnInit{

  errorMessage: string | null = null;
  newYear: number = 0;
  years: YearWithHolyCount[] = [];

  constructor(
    private router: Router,
    private dayoffService: DayoffService
  ) { }

  ngOnInit() {
    this.getAllYears();
  }

  getAllYears() {
    this.dayoffService.getAllYears().subscribe({
      next: (resp) => {
        this.years = resp;
        this.sortYears();
        console.log(resp)
      },
      error: (err) => console.log(err)
    });
  }

  addNewYear(yearToAdd: number) {
    let isYearExists: boolean;
    this.errorMessage = '';
    this.dayoffService.isYearExists(yearToAdd).subscribe({
      next: (resp)=> {
        isYearExists = resp
        if(isYearExists == true){
          this.errorMessage = 'Dany rok juz istnieje'
        }
        else {
          this.years.push({year: yearToAdd, holyCount: 0})
        }
      }
      , error: (err) => console.log(err)
      },
    )
  }

  sortYears() {
    this.years.sort((a, b) => b.year - a.year);
  }

  navigateToYear(year: number) {
    this.router.navigate([`/year/${year}`]);
  }
}

