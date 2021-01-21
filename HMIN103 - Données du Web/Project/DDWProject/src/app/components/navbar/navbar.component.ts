import { Component, OnInit   } from '@angular/core'              ;
import { Router              } from '@angular/router'            ;
import { AuthService         } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user: Object;

  constructor(
    public  authService : AuthService,
    private router      : Router,
    ) { }

  ngOnInit(): void {
    this.authService.getProfile().subscribe((profile: any) => {
      this.user = profile.user;
    },
    err => {
      console.log(err);
      return false;
    });

  }

  onLogoutClick(){
    this.authService.logout();
    console.log('You are logged out');
    this.router.navigate(['/login']);
    return false;
  }

}
