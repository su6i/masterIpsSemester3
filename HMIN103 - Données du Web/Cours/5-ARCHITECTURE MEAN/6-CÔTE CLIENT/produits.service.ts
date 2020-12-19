import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

/*
const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type":  "application/json",
    "Access-Control-Allow-Origin": "*"
  })
};
*/

@Injectable({
  providedIn: 'root'
})
export class ProduitsService {

    private urlBase: string = 'http://localhost:8888/';

    constructor(private http: HttpClient) { }

    getProduits(): Observable<any> {
        return this.http.get(this.urlBase+'produits');
    }

    getCategories(): Observable<any> {
        return this.http.get(this.urlBase+'types');
    }

    getProduitsParCategorie(categorie): Observable<any> {
        return this.http.get(this.urlBase+'produits/'+categorie);
    }
}
