import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { CreateCategoryRequest } from '../models/Category';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/v1';

  getCategories(): Observable<any> {
    return this.http.get(`${this.baseUrl}/category`);
  }

  createCategory(req: CreateCategoryRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/category`, req)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    return throwError(() => 'Something went wrong; please try again later.');
  }

}
