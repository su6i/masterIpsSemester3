import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LendService {

  aid: string;
  quantity: number;


  constructor(private http: HttpClient) { }

  onAddToCard(aid: string): Observable<any>{
    let url = `http://localhost:8888/lends`;
    return this.http.post(
      url, {
      "aid": aid,
      "quantity" : 1
    })
  }
  getCartItems(){
    return this.http.get('http://localhost:8888/lends')
  }

  removeCartItems(aid: string): Observable<any>{
      let url = `http://localhost:8888/lends/${aid}`;
      return this.http.delete(url)
    }


    // Should be completed
  updateCartItems(aid: string): Observable<any>{
      let url = `http://localhost:8888/lends/${aid}`;
      return this.http.patch(url, {})
    }

    getCartItemById(lid){
      return this.http.get('http://localhost:8888/lends'+lid)
    }
}


