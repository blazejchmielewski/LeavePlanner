import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../models/auth.model';
import { Dayoff, DayoffModel, YearWithHolyCount } from '../models/leave.model';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class DayoffService {

  constructor(private http: HttpClient) { }

  apiUrl = `${environment.apiUrl}/dayoff`

  addNewDayoff(request: DayoffModel):Observable<AuthResponse>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<AuthResponse>(`${this.apiUrl}/add`, request, requestOptions)
  }

  getDaysByYear(year: string):Observable<Dayoff[]>{
    return this.http.get<Dayoff[]>(`${this.apiUrl}/daysoff/${year}`)
  }

  getDayOffByDate(date: Date): Observable<Dayoff> {
    const validDate = date instanceof Date ? date : new Date(date);
    const formattedDate = validDate.toISOString().split('T')[0];
    const requestBody = { date: formattedDate };
    
    console.log("wysyłam date " + requestBody)
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<Dayoff>(`${this.apiUrl}/get-by-date`, requestBody, requestOptions);
  }

  getAllYears(){
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<YearWithHolyCount[]>(`${this.apiUrl}/all-years`, requestOptions);
  }

  deleteDayOff(id: number): Observable<String> {
    const requestOptions = {
      params: new HttpParams().set('id', id.toString()),
      withCredentials: true
    };
    return this.http.delete<String>(`${this.apiUrl}/delete`, requestOptions);
  }

  isYearExists(year: number): Observable<boolean>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<boolean>(`${this.apiUrl}/year-exists`, requestOptions);
  }
}
