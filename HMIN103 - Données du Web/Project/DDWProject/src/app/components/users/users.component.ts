import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute            } from '@angular/router'                 ;
import { AuthService, AlertService       } from '@app/services'     ;
// import { AlertService       } from '../../services/alert.service'     ;
import { first } from 'rxjs/operators';
import { User } from '../../../../models/user';
import { AbstractControlOptions, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MustMatch } from '../../_helpers/must-match.validator';


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  // users: Object[];
  form!: FormGroup;
  id!: string;
  users!: User[];
  user: User;
  items = [];
  pageOfItems: Array<any>;
  mode: string;
  loading = false;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService

    ) { }


  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.authService.getAllUsers().subscribe((users:any) => {
      this.users = users;
    },
    err => {
      console.log(err);
      return false;
    }
    );


    // this.authService.getAll()
    // .pipe(first())
    // .subscribe(users => this.users = users);

  this.mode = 'update';


  }

  // update current page of items
  onChangePage(pageOfItems: Array<any>) {
    this.pageOfItems = pageOfItems;
}

removeUser(id) {
  this.authService.removeUser(id)
  .subscribe((user) => {
    this.users = this.users.filter((user:any) => {
      return user._id != id;
    })
  });
}


getUser(id: string){
  this.authService.getUser(id).subscribe((user:any) => {
    this.user = user;
  },
  err => {
    console.log(err);
    return false;
  }
  );

}




deleteUser(id: string) {
  const user = this.users.find(x => x['_id'] === id);
  if (!user) return;
  // user.isDeleting = true;
  this.authService.delete(id)
      .pipe(first())
      .subscribe(() => this.users = this.users.filter(x => x['_id'] !== id));
}


// update(id: string, params: any) {
//   return this.http.put(`${this.baseURL}/users/${id}`, params);
// }


updateUser(id: string) {
  console.log('id::', id);
  this.authService.update(id, this.form.value)
      .pipe(first())
      .subscribe(() => {
          this.alertService.success('User updated', { keepAfterRouteChange: true });
          this.router.navigate(['../../'], { relativeTo: this.route });
      })
      .add(() => this.loading = false);
}





}
