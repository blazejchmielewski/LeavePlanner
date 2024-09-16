import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../models/auth.model';
import { Dayoff, DayoffDetails, Year, YearRequest } from '../models/leave.model';
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

  getAllYearsDetails():Observable<Year[]>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<Year[]>(`${environment.apiUrl}/year/get/all`)
  }

  addNewYear(body: YearRequest):Observable<String>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<String>(`${environment.apiUrl}/year/add`, body, requestOptions)
  }

  getDayOffByDate(date: Date): Observable<DayoffDetails> {
    const validDate = date instanceof Date ? date : new Date(date);
    const formattedDate = validDate.toISOString().split('T')[0];
    const requestBody = { date: formattedDate };
    console.log(formattedDate)
    const requestOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      withCredentials: true
    };
    return this.http.post<DayoffDetails>(`${this.apiUrl}/get-by-date`, requestBody, requestOptions);
  }

  deleteDayOff(id: number): Observable<String> {
    const requestOptions = {
      params: new HttpParams().set('id', id.toString()),
      withCredentials: true
    };
    
    return this.http.delete<String>(`${this.apiUrl}/delete`, requestOptions);
  }
  
}
