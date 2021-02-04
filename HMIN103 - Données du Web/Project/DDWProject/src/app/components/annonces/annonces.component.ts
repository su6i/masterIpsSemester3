import { Component, OnInit, EventEmitter, Output } from '@angular/core'              ;
import { HttpClient                     } from '@angular/common/http'                ;
import { ActivatedRoute, Params, Router } from '@angular/router'                     ;
import { Annonce                        } from '../../../../models/DataInterface'    ;
import { AnnonceService                 } from '../../services/annonce.service'      ;
import { LendService, DataService, AuthService } from '@app/services'                ;

@Component({
  selector: 'app-annonces',
  templateUrl: './annonces.component.html',
  styleUrls: ['./annonces.component.css']
})
export class AnnoncesComponent implements OnInit {

  annonces: Annonce[];
  item: object;
  id:string;
  posts: Annonce[];
  selectedPost: Annonce;
  count: number;
  user: Object;



  // @Output()
  // removeDetailsAnnonce:EventEmitter<Annonce> = new EventEmitter<Annonce>();


constructor(
  private http: HttpClient,
  public annonceService: AnnonceService,
  public lendService: LendService,
  private dataService: DataService,
  public  authService : AuthService,

  private router: Router) {

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


selectPost(post: Annonce){
  console.log('Selected POST: ', post);
  this.selectedPost = post;
}



}


