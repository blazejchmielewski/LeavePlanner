import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { AuthResponse, ChangePasswordData, IUser, LoggedInResponse, LoginData, RegisterData, ToResetPasswordData } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = `${environment.apiUrl}/auth`;

  constructor(private http: HttpClient) { }

  login(body: LoginData): Observable<IUser>{
    return this.http.post<IUser>(`${this.apiUrl}/login`, body,
      {withCredentials: true}
    );
  }

  register(body: RegisterData): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, body);
  }

  logout(): Observable<AuthResponse>{
    return this.http.get<AuthResponse>(`${this.apiUrl}/logout`,
      {withCredentials: true}
    );
  }

  isLoggedIn(): Observable<LoggedInResponse>{
    return this.http.get<LoggedInResponse>(`${this.apiUrl}/logged-in`,
      {withCredentials: true}
    );
  }

  autoLogin(): Observable<IUser>{
    return this.http.get<IUser>(`${this.apiUrl}/auto-login`,
      {withCredentials: true}
    );
  }

  activateAccount(uid: string): Observable<AuthResponse>{
    const params = new HttpParams().append('uid', uid);
    return this.http.get<AuthResponse>(`${this.apiUrl}/activate`, {
      params
    });
  }

  toResetPassword(body: ToResetPasswordData): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/reset-password`, body);
  }

  changePassword(body: ChangePasswordData): Observable<AuthResponse>{
    return this.http.put<AuthResponse>(`${this.apiUrl}/change-password`, body);
  }
}
