import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/v1';


  getAccountBalance(): Observable<any> {
    return this.http.get(`${this.baseUrl}/accountBalance`);
  }
}
