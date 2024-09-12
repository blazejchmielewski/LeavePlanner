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

  getAllReplacements(): Observable<LeaveDataDetailsExtended[]>{
    const requestOptions = {
      withCredentials: true
    };
    return this.http.get<LeaveDataDetailsExtended[]>(`${this.apiUrl}/all-replacements`, requestOptions);
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

  acceptReplacement(uuid: UuidObject): Observable<LeaveDataDetailsExtended> {
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<LeaveDataDetailsExtended>(`${this.apiUrl}/accept-replacement`,uuid, {
      ...requestOptions,
    });
  }

  rejectReplacement(uuid: UuidObject): Observable<LeaveDataDetailsExtended> {
    const requestOptions = {
      withCredentials: true
    };
    return this.http.post<LeaveDataDetailsExtended>(`${this.apiUrl}/reject-replacement`,uuid, {
      ...requestOptions,
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

  

  translateStatus(status: string): string {
    switch (status) {
      case 'PENDING': return 'OCZEKUJĄCY';
      case 'APPROVED_BY_REPLACER': return 'ZAAKCEPTOWANY PRZEZ ZASTEPCE';
      case 'APPROVED_BY_ACCEPTOR': return 'ZAAKCEPTOWANY PRZEZ ZARZĄD';
      case 'REJECTED_BY_REPLACER': return 'ODRZUCONY PRZEZ ZASTĘPCE';
      case 'REJECTED_BY_ACCEPTOR': return 'ODRZUCONY PRZEZ ZARZĄD';
      case 'IN_PROGRESS': return 'ROZPATRYWANY';
      case 'CANCELLED': return 'ANULOWANY';
      default: return '';
    }
  }

  translateType(type: string): string{
    switch(type){
      case 'SICK_LEAVE': return 'ZDROWOTNY';
      case 'ANNUAL_LEAVE': return 'WYPOCZYNKOWY';
      case 'MATERNITY_LEAVE': return 'MACIERZYŃSKI';
      case 'PATERNITY_LEAVE': return 'OJCOWSKI';
      case 'UNPAID_LEAVE': return 'NIEPŁATNY';
      case 'OTHER': return 'INNY';
    }
    return ''
  }
}
