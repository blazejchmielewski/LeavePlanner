import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { DayoffRequest } from 'src/app/modules/core/models/forms.model';
import { Dayoff, DayoffDetails } from 'src/app/modules/core/models/leave.model';
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
    console.log(this.currentDayOffs)
    let dateFromForm: Dayoff = this.initDayoffForm.getRawValue();
    
    const isDuplicate = this.currentDayOffs
    .some(date => date.dayOff.getFullYear + '-' + date.dayOff.getMonth + '-' + date.dayOff.getDay === 
      dateFromForm.dayOff.getFullYear + '-' + dateFromForm.dayOff.getMonth + '-' + dateFromForm.dayOff.getDay);
    console.log(isDuplicate)
    if (isDuplicate) {
      alert('Święto z tą datą już istnieje.');
    } else {
      this.dayoffService.addNewDayoff(this.currentDayOffs).subscribe({
        next: (resp) => {
          //this.getDaysOff(this.currentYear!);
        },
        error: (err) => console.log(err),
      });
    }
  }
  
  
  navigateToCalendarPanel() {
    this.router.navigate(['/calendar-panel']);
  }

  get controls() {
    return this.initDayoffForm.controls;
  }

  onDeleteDayOff(date: Date) {
    this.dayoffService.getDayOffByDate(date).subscribe({
      next: (resp: DayoffDetails) => {
        if (resp) {
          const idToDelete: number = resp.id;
          this.dayoffService.deleteDayOff(idToDelete).subscribe({
            next: (resp) => {
              console.log(resp);
              this.getDaysOff(this.currentYear!); // Odśwież listę po usunięciu
            },
            error: (err) => console.log(err)
          });
        }
      },
      error: (err) => console.log(err)
    });
  }

  isDateDuplicate(dateToCheck: Date): boolean {
    // Konwertuj dateToCheck na format ISO bez części czasu
    const formattedDateToCheck = new Date(dateToCheck).toISOString().split('T')[0];
  
    // Sprawdź każdą datę w tabeli
    const isDuplicate = this.currentDayOffs.some(dayoff => {
      const existingDate = new Date(dayoff.dayOff);
      
      // Konwertuj istniejącą datę na format ISO bez części czasu
      const formattedExistingDate = existingDate.toISOString().split('T')[0];
      
      // Porównaj daty
      return formattedExistingDate === formattedDateToCheck;
    });
  
    return isDuplicate;
  }


}

