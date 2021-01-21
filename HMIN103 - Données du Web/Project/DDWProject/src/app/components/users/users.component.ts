import { Component, OnInit } from '@angular/core';
import { Router            } from '@angular/router'                 ;
import { AuthService       } from '../../services/auth.service'     ;

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: Object[];
  items = [];
  pageOfItems: Array<any>;


  constructor(
      private authService: AuthService) { }


  ngOnInit(): void {
    // an example array of 150 items to be paged
    this.items = Array(150).fill(0).map((x, i) => ({ id: (i + 1), name: `Item ${i + 1}`}));

    this.authService.getAllUsers().subscribe((users:any) => {
      this.users = users;
    },
    err => {
      console.log(err);
      return false;
    }
    );
  }

  onChangePage(pageOfItems: Array<any>) {
    // update current page of items
    this.pageOfItems = pageOfItems;
}

removeUser(uid) {
  this.authService.removeUser(uid)
  .subscribe((user) => {
    this.users = this.users.filter((user:any) => {
      return user._id != uid;
    })
  });
}




}
