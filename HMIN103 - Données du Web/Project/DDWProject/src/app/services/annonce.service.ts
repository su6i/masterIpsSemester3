import { Injectable              } from '@angular/core'       ;
import { Observable              } from 'rxjs'                ;
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AnnoncesComponent } from '@app/components';
const baseUrl = 'http://localhost:8888/';

@Injectable({
  providedIn: 'root'
})



export class AnnonceService {


  constructor(private http: HttpClient,
              public annoncesComponent: AnnoncesComponent) { }
  getAnnonceItems(): Observable<any> {
    return this.http.get(`${baseUrl}annonces/`);
  }

  // aid = Annonce ID
  // lid = Lend ID
  getAnnonceItemById(aid): Observable<any>{
    return this.http.get(`${baseUrl}annonces/${aid}`);
  }
  getCartItemById(lid): Observable<any>{
    return this.http.get(`${baseUrl}lends/${lid}`);
  }

  getCategories(): Observable<any> {
    return this.http.get(`${baseUrl}categories`);
  }

  getTypes(category): Observable<any> {
    return this.http.get(`${baseUrl}categories/category`);
  }
  getSelectedAnnonces(category, type): Observable<any>{
    return this.http.get(`${baseUrl}categories/category/type`);
  }

  removeAnnonce(aid: string): Observable<any>{
    let url = `http://localhost:8888/annonces/${aid}`;
    return this.http.delete(url)
  }

  createAds(params: any) {
    return this.http.post(`${baseUrl}/annonces/`, params);
  }

  updateAds(id: string, params: any) {
      return this.http.patch(`${baseUrl}/annonces/${id}`, params);
  }

  detailsAds(id: string) {
      return this.http.get(`${baseUrl}/annonces/${id}`);
  }


}



