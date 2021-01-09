import { Component, OnInit } from '@angular/core';
import { AuthService       } from '../../services/auth.service'     ;


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user: Object;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getProfile().subscribe((profile: any) => {
      this.user = profile.user;
    },
    err => {
      console.log(err);
      return false;
    }
    );
  }

}
