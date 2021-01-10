import { Component, OnInit   } from '@angular/core'                       ;
import { AuthService         } from '../../../services/auth.service'      ;
import { Router              } from '@angular/router'                     ;


@Component({
  selector: 'app-login-email',
  templateUrl: './login-email.component.html',
  styleUrls: ['./login-email.component.css']
})
export class LoginEmailComponent implements OnInit {


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
          this.router.navigate(['dashboard']);

      } else {
          console.log(data.msg);
            this.router.navigate(['/login']);

      }

   });
  }

}
