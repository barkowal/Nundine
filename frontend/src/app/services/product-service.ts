import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BuyProductRequest, CreateProductRequest, UpdateProductRequest, UpdateProductStockRequest } from '../models/Product';

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

  getShopProducts(): Observable<any> {
    return this.http.get(`${this.baseUrl}/product/shop`);
  }

  buyProduct(req: BuyProductRequest): Observable<any> {
    return this.http.post(`${this.baseUrl}/order`, req)
  }
}
