import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  getProductItems(): Observable<any> {
    return this.http.get('http://localhost:8888/products');
  }

  // pid = Product ID
  getProductItemById(pid): Observable<any>{
    return this.http.get('http://localhost:8888/products/'+pid);
  }
  getCartItemById(oid): Observable<any>{
    return this.http.get('http://localhost:8888/orders/'+oid);
  }

  getCategories(): Observable<any> {
    return this.http.get('http://localhost:8888/categories');
  }

  getTypes(category): Observable<any> {
    return this.http.get('http://localhost:8888/categories/'+category);
  }
  getSelectedProducts(category, type): Observable<any>{
    return this.http.get('http://localhost:8888/categories/'+category+'/'+type);
  }

}
