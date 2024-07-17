import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { AuthResponse } from '../models/auth.model';
import { LeaveData, LeaveDataDetails, LeaveDataDetailsExtended, UsersToSwitch, UuidObject } from '../models/leave.model';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {

  apiUrl = `${environment.apiUrl}/leave`

  constructor(private http: HttpClient) { }

  addLeave(body: LeaveData):Observable<AuthResponse>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<AuthResponse>(`${this.apiUrl}/add`, body, requestOptions)
  }

  getUsersToSwitch(): Observable<UsersToSwitch[]> {
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<UsersToSwitch[]>(`${this.apiUrl}/users-to-switch`, requestOptions);
  }

  getAll(): Observable<LeaveDataDetailsExtended[]> {
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<LeaveDataDetailsExtended[]>(`${this.apiUrl}/all`, {
      ...requestOptions
    });
  }

  getAllUserLeaves(): Observable<LeaveDataDetails[]>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<LeaveDataDetails[]>(`${this.apiUrl}/all-user-leaves`, requestOptions);
  }

  getLeaveByUuid(uuid: string): Observable<LeaveDataDetailsExtended> {
    const params = new HttpParams().set('uuid', uuid);
    const requestOptions = {
      withCredentials: true
    };

    return this.http.get<LeaveDataDetailsExtended>(`${this.apiUrl}/get-by-uuid`, {
      params,
      ...requestOptions
    });
  }

  acceptLeave(uuid: UuidObject): Observable<LeaveDataDetailsExtended> {
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<LeaveDataDetailsExtended>(`${this.apiUrl}/accept`,uuid, {
      ...requestOptions,
    });
  }

  rejectLeave(uuid: UuidObject): Observable<LeaveDataDetailsExtended> {
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<LeaveDataDetailsExtended>(`${this.apiUrl}/reject`, uuid,{
      ...requestOptions
    });
  }
}
