import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.development';
import { UserToTableData } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiUrl = `${environment.apiUrl}/users`;

  constructor(private http: HttpClient) { }

  getAllUsers():Observable<UserToTableData>{
    return this.http.get<UserToTableData>(`${this.apiUrl}/table/all`);
  }
}


