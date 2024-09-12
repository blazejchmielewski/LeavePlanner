import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { DayoffRequest } from 'src/app/modules/core/models/forms.model';
import { Dayoff } from 'src/app/modules/core/models/leave.model';
import { DayoffService } from 'src/app/modules/core/services/dayoff.service';
import { FormService } from 'src/app/modules/core/services/form.service';

@Component({
  selector: 'app-year',
  templateUrl: './year.component.html',
  styleUrls: ['./year.component.css']
})
export class YearComponent implements OnInit{

  daysArray: Dayoff[] = [];
  currentYear: string | null = null;
  initDayoffForm: FormGroup<DayoffRequest> = this.formService.initDayoffForm();
  currentDayOffs: Dayoff[] = [];

  constructor(
  private dayoffService: DayoffService,
  private formService: FormService,
  public router: Router,
  private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.currentDayOffs = []
    this.route.url.subscribe(urlSegment=> {
      const yearSegment = urlSegment[1];
      this.currentYear = yearSegment.path;
    })
    if(this.currentYear){
      this.getDaysOff(this.currentYear);
    } 
  }

  getDaysOff(year: string){
    if(year){
      this.dayoffService.getDaysByYear(year).subscribe({
        next: (resp: Dayoff[])=> {
          this.currentDayOffs = resp
        }, error: (err) => console.log(err)
      })
    }
  }

  onAddNewDayoff(){
    const { holyName, dayOff } = this.initDayoffForm.getRawValue();

    if (holyName && dayOff) {
      const newDayoff: Dayoff = { holyName, dayOff };
      this.daysArray.push(newDayoff);

      this.dayoffService.addNewDayoff(this.daysArray).subscribe({
        next: (resp) => {
          console.log(resp);
          this.daysArray = [];
        },
        error: (err) => console.log(err),
      });
    }
  }

  navigateToCalendarPanel(){
    this.router.navigate(['/calendar-panel'])
  }

  get controls(){
    return this.initDayoffForm.controls
  }
}
