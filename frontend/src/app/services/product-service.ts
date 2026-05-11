import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateProductRequest, UpdateProductRequest, UpdateProductStockRequest } from '../models/Product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api/v1';

  createProduct(req: CreateProductRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/product`, req);
  }

  getProducts(): Observable<any> {
    return this.http.get(`${this.baseUrl}/product`);
  }

  getUserProducts(userId: string | null): Observable<any> {
    return this.http.get(`${this.baseUrl}/product?userId=${userId}`);
  }

  updateProduct(productId: string, req: UpdateProductRequest): Observable<any> {
    return this.http.put(`${this.baseUrl}/product/${productId}`, req);
  }

  deleteProduct(productId: string) {
    return this.http.delete(`${this.baseUrl}/product/${productId}`);
  }

  getProductsStock(): Observable<any> {
    return this.http.get(`${this.baseUrl}/product/stock`);
  }

  updateProductStock(productId: string, req: UpdateProductStockRequest): Observable<any> {
    return this.http.put(`${this.baseUrl}/product/stock/${productId}`, req);
  }

  // TODO:
  // buyProduct(productId: string): Observable<any> {
  // }
}
