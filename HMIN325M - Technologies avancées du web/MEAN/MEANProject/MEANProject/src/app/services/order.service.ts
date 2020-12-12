import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  pid: string;
  quantity: number;


  constructor(private http: HttpClient) { }

  onAddToCard(pid: string): Observable<any>{
    let url = `http://localhost:8888/orders`;
    return this.http.post(
      url, {
      "pid": pid,
      "quantity" : 1
    })
  }
  getCartItems(){
    return this.http.get('http://localhost:8888/orders')
  }

  removeCartItems(pid: string): Observable<any>{
      let url = `http://localhost:8888/orders/${pid}`;
      return this.http.delete(url)
    }


    // Should be completed
  updateCartItems(pid: string): Observable<any>{
      let url = `http://localhost:8888/orders/${pid}`;
      return this.http.patch(url, {})
    }

    getCartItemById(oid){
      return this.http.get('http://localhost:8888/orders'+oid)
    }


}







