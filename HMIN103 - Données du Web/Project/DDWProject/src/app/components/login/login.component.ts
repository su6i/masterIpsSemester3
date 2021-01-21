import { Component, OnInit   } from '@angular/core'                       ;
import { Router              } from '@angular/router'                     ;
import { AuthService         } from '../../services/auth.service'         ;
import { DataService         } from '../../services/data.service'         ;
import { Annonce                } from '../../../../models/DataInterface'    ;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errSwitch = false;
  errMsg: string;


  email: string;
  password: string;


  constructor(
    private authService: AuthService,
    private router: Router,
    ) { }

  ngOnInit(): void {
  }

  onLoginSubmit(){
    const user = {
      email: this.email.toLowerCase(),
      password: this.password
    }
    //this.authService.authenticateUser(user).subscribe(data => {
    this.authService.login(user.email, user.password).subscribe((data: any) => {
    if(data.success){
        this.authService.storeUserData(data.token, data.user);
        console.log('You are now logged in');
          this.router.navigate(['/']);

      } else {
          console.log(data.msg);
            this.router.navigate(['/login']);

      }

   });
  }

}
