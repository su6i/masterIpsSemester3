import { Component, OnInit   } from '@angular/core'                       ;
import { Router              } from '@angular/router'                     ;
import { AuthService         } from '../../services/auth.service'         ;
import { DataService         } from '../../services/data.service'         ;
import { Product             } from '../../../../models/DataInterface'    ;
import { FlashMessagesService} from 'angular2-flash-messages'             ;


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts: Product[]
  count: number;


  constructor(
    private router: Router,
    private flashMessage: FlashMessagesService,
    public  authService : AuthService,
    private dataService: DataService
  ) { }

  ngOnInit() {
    this.dataService.getPosts().subscribe(posts => {
      this.posts = posts['products'];
      this.count = posts['count'];
      this.dataService.postsData = posts
    });
  }

  onSelectedOption(e) {
    this.getFilteredExpenseList();
  }

  getFilteredExpenseList() {
    if (this.dataService.searchOption.length > 0)
      {
        this.posts = this.dataService.filteredListOptions();
      }
      else {
        this.posts = this.dataService.postsData;
      }
  }

  onLogoutClick(){
    this.authService.logout();
    this.flashMessage.show('You are logged out', {
      cssClass: 'alert-sucess',
      timeout : 3000
    });
    this.router.navigate(['/login']);
    return false;
  }

}
