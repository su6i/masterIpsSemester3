import { Component, OnInit   } from '@angular/core'                      ;
import { ValidateService     } from '../../../services/validate.service' ;
import { AuthService         } from '../../../services/auth.service'     ;
import { Router              } from '@angular/router'                    ;

@Component({
  selector: 'app-register-validation-usedng-if-directive',
  templateUrl: './register-validation-usedng-if-directive.component.html',
  styleUrls: ['./register-validation-usedng-if-directive.component.css']
})
export class RegisterValidationUsedngIfDirectiveComponent implements OnInit {


  errSwitch    = false;
  succSwitch   = false;
  submitSwitch = false;
  errMsg       : string;
  succMsg      : string;


  constructor(
    private validateService: ValidateService,
    private authService: AuthService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  // Second form

  onRegisterSubmit2(form) {

    // Validate Email
    if (!this.validateService.validateEmail(form.value.email)) {
      this.errSwitch = true;
      this.errMsg = 'Please use a valid email';
      return false;
    }

    // Register User
    this.authService.registerUser(form.value).subscribe((data: any) => {
      if (data.success) {
        this.succSwitch = true;
        this.succMsg = 'You are now registered and can log in';
        this.errSwitch = false;
        this.errMsg = '';
        this.submitSwitch = true;
        this.router.navigate(['/login']);
      } else {
        this.succSwitch = false;
        this.succMsg = '';
        this.errSwitch = true;
        this.errMsg = 'Something went wrong';
      }
    });

  }

}
