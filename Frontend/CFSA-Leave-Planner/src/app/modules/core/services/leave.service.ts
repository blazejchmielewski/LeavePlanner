import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { LeaveRequest } from '../models/forms.model';
import { AuthResponse } from '../models/auth.model';
import { LeaveData, UsersToSwitch } from '../models/leave.model';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {

  apiUrl = environment.apiUrl

  constructor(private http: HttpClient) { }

  addLeave(body: LeaveData):Observable<AuthResponse>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<AuthResponse>(`${this.apiUrl}/leave/add`, body, requestOptions)
  }

  getUsersToSwitch(): Observable<UsersToSwitch[]> {
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<UsersToSwitch[]>(`${this.apiUrl}/leave/users-to-switch`, requestOptions);
  }
}
