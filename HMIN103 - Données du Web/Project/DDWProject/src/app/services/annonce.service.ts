import { Injectable              } from '@angular/core'       ;
import { Observable              } from 'rxjs'                ;
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  constructor(private http: HttpClient) { }
  getAnnonceItems(): Observable<any> {
    return this.http.get('http://localhost:8888/annonces');
  }

  // aid = Annonce ID
  // lid = Lend ID
  getAnnonceItemById(aid): Observable<any>{
    return this.http.get('http://localhost:8888/annonces/'+aid);
  }
  getCartItemById(lid): Observable<any>{
    return this.http.get('http://localhost:8888/lends/'+lid);
  }

  getCategories(): Observable<any> {
    return this.http.get('http://localhost:8888/categories');
  }

  getTypes(category): Observable<any> {
    return this.http.get('http://localhost:8888/categories/'+category);
  }
  getSelectedAnnonces(category, type): Observable<any>{
    return this.http.get('http://localhost:8888/categories/'+category+'/'+type);
  }

  removeAnnonce(aid: string): Observable<any>{
    let url = `http://localhost:8888/annonces/${aid}`;
    return this.http.delete(url)
  }

}



