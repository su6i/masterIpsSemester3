import { Injectable                           } from '@angular/core'                    ;
import { HttpClient, HttpHeaders              } from '@angular/common/http'             ;
import { tap                                  } from 'rxjs/operators'                   ;
import { Observable                           } from 'rxjs'                             ;



@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseURL: string = "http://localhost:8888";

  authToken: any;
  user: any;

  constructor(private http: HttpClient) {

    this.loadToken()
  }




  registerUser(user){
    user.username = user.username.toLowerCase();
    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    return this.http.post(this.baseURL+'/users/register', user, { headers });
  }

  authenticateUser(user){
    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    return this.http.post(this.baseURL+'/users/authenticate', user, { headers });
  }

  // Second method for authenticateUser
  login(email:string, password:string) {
    return this.http.post<{id_token:  string}>(this.baseURL+'/users/authenticate', {email, password})
      .pipe(tap(res => {
                  localStorage.setItem('id_token', res.id_token);
      }))
  }

  // Second method for registerUser
  register(email:string, password:string) {
    return this.http.post<{access_token: string}>(this.baseURL+'/users/authenticate', {email, password})
      .pipe(tap(res => {
                  this.login(email, password)
      }))
  }




  getProfile(){
    // this.loadToken();

    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    headers = headers.append('Authorization', this.authToken);

    return this.http.get(this.baseURL+'/users/profile', { headers });

  }

  getProfile2() {
    // this.loadToken();

    const httpOptionsAuthorized = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
         Authorization: this.authToken
      })
    };

    return this.http.get('http://localhost:8888/users/profile', {headers: httpOptionsAuthorized.headers});
  }

  getAllUsers(){
    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');
    headers = headers.append('Authorization', this.authToken);

    return this.http.get(this.baseURL+'/users', { headers });

  }



  storeUserData(token, user){
    localStorage.setItem('id_token', token);
    localStorage.setItem('user', JSON.stringify(user));
    this.authToken = token;
    this.user      = user ;
  }

  loadToken(){
    const token = localStorage.getItem('id_token');
    this.authToken = token;
  }


  loggedIn() {
    if (this.authToken) {
      return true;
    } else {
      return false;
    }
  }

  // Second method for loggedIn
  public get loggedIn2(): boolean{
    return localStorage.getItem('id_token') !==  null;
  }

  logout(){
    this.authToken = null;
    this.user      = null;
    localStorage.clear();
  }

  // Second method for logout
  logout2(){
      localStorage.removeItem('id_token');
  }


  removeUser(uid: string): Observable<any>{
    let url = `http://localhost:8888/users/${uid}`;
    return this.http.delete(url)
  }






}
