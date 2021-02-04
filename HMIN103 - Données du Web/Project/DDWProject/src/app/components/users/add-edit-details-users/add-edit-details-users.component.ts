import { Component, OnInit, Output, EventEmitter,  Input  } from '@angular/core';
import { AuthService                                      } from 'src/app/services/auth.service';
import { UsersComponent                                   } from 'src/app/components/users/users.component'
import { User } from 'models/user';
@Component({
  selector: 'app-add-edit-details-users',
  templateUrl: './add-edit-details-users.component.html',
  styleUrls: ['./add-edit-details-users.component.css']
})
export class AddEditDetailsUsersComponent implements OnInit {

  @Input()  user: User;

  constructor(public authService: AuthService, public usersComponent: UsersComponent) { }

  ngOnInit(): void {

   // get id of current user and display its information.
  // The information will displayed in bottom part of parent url.


  }

  // updateUser(id:string){}
  // getUser(id:string){}
  // removeUser(id:string){}



}
