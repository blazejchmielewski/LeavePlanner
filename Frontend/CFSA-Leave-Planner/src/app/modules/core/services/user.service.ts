import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { EditUserData, UserAllData, UserDetails } from '../models/user.model';
import { AuthResponse, User } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = `${environment.apiUrl}/users`;

  private currentUser: UserAllData | null = null;
  private userUpdated = new Subject<User>();

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<UserAllData[]> {
    return this.http.get<UserAllData[]>(`${this.apiUrl}/table/all`, { withCredentials: true });
  }

  expireUser(id: number): Observable<AuthResponse> {
    console.log("ez")
    return this.http.put<AuthResponse>(`${this.apiUrl}/expire/${id}`, {});
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  edit(id: number, body: EditUserData): Observable<AuthResponse> {
    return this.http.put<AuthResponse>(`${this.apiUrl}/update/${id}`, body);
  }

  setCurrentUser(user: UserAllData): void {
    this.currentUser = user;
  }

  getCurrentUser(): UserAllData | null {
    return this.currentUser;
  }

  departments(): Observable<string[]>{
    return this.http.get<string[]>(`${this.apiUrl}/departments`)
  }
  
  getUserDetails():Observable<UserDetails>{
    return this.http.get<UserDetails>(`${this.apiUrl}/user-details`,
      {withCredentials: true}
    )
  }
}


