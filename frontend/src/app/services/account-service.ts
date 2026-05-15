import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DepositAccountBalanceRequest } from '../models/AccountBalance';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/v1';


  getAccountBalance(): Observable<any> {
    return this.http.get(`${this.baseUrl}/accountBalance`);
  }

  depositToAccountBalance(request: DepositAccountBalanceRequest): Observable<any> {
    return this.http.patch(`${this.baseUrl}/accountBalance/deposit`, request);
  }
}
