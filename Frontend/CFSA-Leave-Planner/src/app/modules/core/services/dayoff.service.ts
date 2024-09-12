import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../models/auth.model';
import { Dayoff } from '../models/leave.model';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class DayoffService {

  constructor(private http: HttpClient) { }

  apiUrl = `${environment.apiUrl}/dayoff`

  addNewDayoff(request: Dayoff[]):Observable<AuthResponse>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<AuthResponse>(`${this.apiUrl}/add`, request, requestOptions)
  }

  getDaysByYear(year: string):Observable<Dayoff[]>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<Dayoff[]>(`${this.apiUrl}/daysoff/${year}`)
  }
}
