import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DayoffService } from '../../../services/dayoff.service';
import { Year, YearRequest } from '../../../models/leave.model';

@Component({
  selector: 'app-calendar-panel',
  templateUrl: './calendar-panel.component.html',
  styleUrls: ['./calendar-panel.component.css']
})
export class CalendarPanelComponent implements OnInit{

  years: Year[] = [];
  yearRequest: YearRequest | null = null;
  errorMessage: string | null = null;
  newYear: number = 0;

  constructor(
    private router: Router,
    private dayoffService: DayoffService
  ) { }

  ngOnInit() {
    this.getAllYearsDetails();
  }

  getAllYearsDetails() {
    this.dayoffService.getAllYearsDetails().subscribe({
      next: (resp) => {
        this.years = resp;
        this.sortYears();
      },
      error: (err) => console.log(err)
    });
  }

  addNewYear(year: number) {
    // Sprawdź, czy rok już istnieje w liście lat
    const yearExists = this.years.some(existingYear => existingYear.year === year);

    if (yearExists) {
      this.errorMessage = `Rok ${year} już istnieje.`;
      return; // Zatrzymaj dalsze działanie metody
    }

    this.errorMessage = null; // Wyczyść ewentualne poprzednie błędy

    this.yearRequest = { year: year };
    this.dayoffService.addNewYear(this.yearRequest).subscribe({
      next: (response) => {
        console.log('Year added successfully:', response);
        this.getAllYearsDetails(); // Odśwież listę lat
      },
      error: (err) => {
        console.log('Error adding year:', err);
        // Obsłuż ewentualne błędy tutaj, jeśli potrzebne
      }
    });
  }

  sortYears() {
    this.years.sort((a, b) => b.year - a.year);
  }

  navigateToYear(year: number) {
    this.router.navigate([`/year/${year}`]);
  }
}

