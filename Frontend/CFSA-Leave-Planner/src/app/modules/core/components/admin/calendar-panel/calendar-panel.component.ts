import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-calendar-panel',
  templateUrl: './calendar-panel.component.html',
  styleUrls: ['./calendar-panel.component.css']
})
export class CalendarPanelComponent {

  constructor(private router: Router){}

  navigateToYear(year:number){
    this.router.navigate([`/year/${year}`])
  }
}

