import { Injectable              } from '@angular/core'                     ;
import { Observable              } from 'rxjs'                              ;
import { HttpClient, HttpHeaders } from '@angular/common/http'              ;
import { Annonce                    } from '../../../models/DataInterface'     ;


@Injectable({
  providedIn: 'root'
})
export class DataService {

  searchOption=[]
  public postsData: Annonce[]
  postUrl : string = "http://localhost:8888/annonces";

  constructor(
    private http: HttpClient
  ) { }


  getPosts(): Observable<Annonce[]>{
    return this.http.get<Annonce[]>(this.postUrl);
  }

  filteredListOptions() {
    let posts = this.postsData;
        let filteredPostsList = [];
        for (let post of posts['annonces']) {
            for (let options of this.searchOption) {
                if (options.name === post.name) {
                  filteredPostsList.push(post);
                }
            }
        }
        // console.log(filteredPostsList);
        return filteredPostsList;
  }
}
