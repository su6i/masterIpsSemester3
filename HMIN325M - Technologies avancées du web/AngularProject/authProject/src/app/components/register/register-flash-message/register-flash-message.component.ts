import { Component, OnInit   } from '@angular/core'                   ;
import { ValidateService     } from '../../../services/validate.service' ;
import { AuthService         } from '../../../services/auth.service'     ;
import { FlashMessagesService} from 'angular2-flash-messages'         ;
import { Router              } from '@angular/router'                 ;

@Component({
  selector: 'app-register-flash-message',
  templateUrl: './register-flash-message.component.html',
  styleUrls: ['./register-flash-message.component.css']
})
export class RegisterFlashMessageComponent implements OnInit {



  name         : String;
  username     : String;
  email        : String;
  password     : String;
  msg          : "";

  constructor(
    private validateService: ValidateService,
    private flashMessage: FlashMessagesService,
    private authService: AuthService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  onRegisterSubmit(){
    const user = {
      name     : this.name,
      email    : this.email,
      username : this.username,
      password : this.password

    }

    // Required Fields
    if(!this.validateService.validateRegister(user)){
      this.flashMessage.show('Please fill in all fields',
                {cssClass: 'alert-danger, timeout: 3000'});
      // console.log('Please fill in all fields');
      return false;
    }

    // Validate Email
    if(!this.validateService.validateEmail(user.email)){
      this.flashMessage.show('Please use a valid email',
      {cssClass: 'alert-danger, timeout: 3000'});
      // console.log('Please use a valid email');
      return false;
    }

  // Register a user
  this.authService.registerUser(user).subscribe((data: any) => {
      if(data.success){
        this.flashMessage.show('You are now registerd and can log in',
                {cssClass: 'alert-success, timeout: 3000'});
        console.log('You are now registerd and can log in');
        // setTimeout(() => {this.router.navigate(['/']);}, 1000);
        this.router.navigate(['/login']);

      } else {

        this.flashMessage.show('Something went wrong',
                {cssClass: 'alert-danger, timeout: 3000'});
        console.log('Something went wrong, regisration failed');
        this.router.navigate(['/register']);

      }
    });
  }


}
