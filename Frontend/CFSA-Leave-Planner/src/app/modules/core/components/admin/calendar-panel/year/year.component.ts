import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { DayoffRequest } from 'src/app/modules/core/models/forms.model';
import { Dayoff, DayoffModel } from 'src/app/modules/core/models/leave.model';
import { DayoffService } from 'src/app/modules/core/services/dayoff.service';
import { FormService } from 'src/app/modules/core/services/form.service';

@Component({
  selector: 'app-year',
  templateUrl: './year.component.html',
  styleUrls: ['./year.component.css']
})
export class YearComponent implements OnInit{

  currentYear: string | null = null;
  initDayoffForm: FormGroup<DayoffRequest> = this.formService.initDayoffForm();
  currentDayOffs: Dayoff[] = [];
  minDate: Date | null = null;
  maxDate: Date | null = null;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private dayoffService: DayoffService,
    private formService: FormService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.url.subscribe(urlSegment => {
      const yearSegment = urlSegment[1];
      this.currentYear = yearSegment.path;
      if (this.currentYear) {
        this.getDaysOff(this.currentYear);
        this.setCalendarYearLimits(parseInt(this.currentYear, 10));
      }
    });
  }

  getDaysOff(year: string) {
    if (year) {
      this.dayoffService.getDaysByYear(year).subscribe({
        next: (resp: Dayoff[]) => {
          this.currentDayOffs = resp;
        },
        error: (err) => console.log(err),
      });
    }
  }

  setCalendarYearLimits(year: number) {
    this.minDate = new Date(year, 0, 1);
    this.maxDate = new Date(year, 11, 31);
  }

  onAddNewDayoff() {
    let formValues = this.initDayoffForm.getRawValue();
    let dayOffDate = new Date(formValues.dayOff);
    dayOffDate.setHours(0, 0, 0, 0);
    dayOffDate.setDate(dayOffDate.getDate() + 1);
    let dayoffModel: DayoffModel = {
      holyName: formValues.holyName,
      dayOff: dayOffDate
    };

    this.dayoffService.addNewDayoff(dayoffModel).subscribe({
      next: (resp) => {
        this.errorMessage = null; 
        this.successMessage = 'Dzień wolny został dodany pomyślnie!';
        if(this.currentYear){
          this.getDaysOff(this.currentYear);
        }
      },
      error: (err) => {
        this.successMessage = null;
        this.errorMessage = 'Podana data już istnieje';
        console.log(err); 
      }
    });
  }
  
  navigateToCalendarPanel() {
    this.router.navigate(['/calendar-panel']);
  }

  get controls() {
    return this.initDayoffForm.controls;
  }

  onDeleteDayOff(date: Date) {
    this.dayoffService.getDayOffByDate(date).subscribe({
      next: (resp: Dayoff) => {
        if (resp) {
          const idToDelete: number = resp.id;
          this.dayoffService.deleteDayOff(idToDelete).subscribe({
            next: (resp) => {
              if(this.currentYear){
                this.getDaysOff(this.currentYear);
              }
            },
            error: (err) => {
              console.log(err)
              if(this.currentYear){
                this.getDaysOff(this.currentYear);
              }
            }
          });
        }
      },
      error: (err) => console.log(err)
    });
  }
}

