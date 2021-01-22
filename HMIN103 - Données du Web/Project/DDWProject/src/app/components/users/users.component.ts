import { Component, OnInit } from '@angular/core';
import { Router            } from '@angular/router'                 ;
import { AuthService       } from '../../services/auth.service'     ;
import { first } from 'rxjs/operators';
import { User } from '../../../../models/user';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  // users: Object[];
  users!: User[];
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


    this.authService.getAll()
    .pipe(first())
    .subscribe(users => this.users = users);




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







deleteUser(id: string) {
  const user = this.users.find(x => x['_id'] === id);
  if (!user) return;
  // user.isDeleting = true;
  this.authService.delete(id)
      .pipe(first())
      .subscribe(() => this.users = this.users.filter(x => x['_id'] !== id));
}








}
