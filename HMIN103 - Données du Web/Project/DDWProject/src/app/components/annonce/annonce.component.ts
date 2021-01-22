import { Component, OnInit, EventEmitter, Output } from '@angular/core'              ;
import { HttpClient                     } from '@angular/common/http'                ;
import { ActivatedRoute, Params, Router } from '@angular/router'                     ;
import { Annonce                        } from '../../../../models/DataInterface'    ;
import { AnnonceService                 } from '../../services/annonce.service'      ;
import { LendService                    } from '../../services/lend.service'         ;
import { DataService                    } from '../../services/data.service'         ;
import { BsDatepickerConfig             } from 'ngx-bootstrap/datepicker'            ;
import { AuthService         } from '../../services/auth.service';

@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrls: ['./annonce.component.css']
})
export class AnnonceComponent implements OnInit {

  annonces: Annonce[];
  item: object;
  id:string;
  posts: Annonce[];
  count: number;
  start : Date = new Date(2021,1,23);
  end : Date = new Date(2021,1,31);
  dateRange: Date[];
  days: number;
  user: Object;



  // @Output()
  // removeDetailsAnnonce:EventEmitter<Annonce> = new EventEmitter<Annonce>();

  datePickerConfig: Partial<BsDatepickerConfig>;

constructor(
  private http: HttpClient,
  private annonceService: AnnonceService,
  private lendService: LendService,
  private dataService: DataService,
  public  authService : AuthService,

  private router: Router) {

    this.datePickerConfig =Object.assign({},{
      containerClass: 'theme-dark-blue',
      minDate: new Date(2021,1,23),
      maxDate: new Date(2021,2,31),
      dateInputFormat: 'DD/MM/YYYY',
      showTodayButton: true,
      maxDateRange: 10,
      isAnimated: true,

    });

    this.dateRange = [this.start, this.end];
    this.days = this.getDays();
   }

ngOnInit(){

  this.dataService.getPosts().subscribe(posts => {
    this.posts = posts['annonces'];
    this.dataService.postsData = posts
  });

  this.authService.getProfile().subscribe((profile: any) => {
    this.user = profile.user;
  },
  err => {
    console.log(err);
    return false;
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

addCartItems(aid) {
  this.lendService.onAddToCard(aid).subscribe();
  this.router.navigate(['/lends/']);
  return this.days;
  }

getAnnonceItemById(aid){
  this.annonceService.getAnnonceItemById(aid).subscribe(annonce=>{
    this.item = annonce;
  });
  this.router.navigate(['/annonces/',aid]);
}

removeAnnonce(aid) {
  this.annonceService.removeAnnonce(aid).subscribe((annonce) => {
    this.posts = this.posts.filter((annonce:Annonce) => {
      return annonce['_id'] != aid;
    })
  });

  // this.router.navigate(['/annonces/']);
}


lendDays(date1:any, date2:any) {
	return new Date(date2 - date1).getDate() - 1
}

getDays(){
  return  this.lendDays(this.dateRange[0],this.dateRange[1]);
}


}


