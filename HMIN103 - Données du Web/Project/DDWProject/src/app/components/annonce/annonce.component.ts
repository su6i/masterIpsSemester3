import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { HttpClient                     } from '@angular/common/http'                ;
import { ActivatedRoute, Params, Router } from '@angular/router'                     ;
import { Annonce                        } from '../../../../models/DataInterface'    ;
import { AnnonceService                 } from '../../services/annonce.service'      ;
import { LendService                    } from '../../services/lend.service'        ;
import { DataService                    } from '../../services/data.service'         ;

@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrls: ['./annonce.component.css']
})
export class AnnonceComponent implements OnInit {

  annonces: Annonce[];
  item: object;
  id:string;
  posts: Annonce[]
  count: number;

  // @Output()
  // removeDetailsAnnonce:EventEmitter<Annonce> = new EventEmitter<Annonce>();


constructor(
  private http: HttpClient,
  private annonceService: AnnonceService,
  private lendService: LendService,
  private dataService: DataService,
  private router: Router) { }

ngOnInit(){

  this.dataService.getPosts().subscribe(posts => {
    this.posts = posts['annonces'];
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

addCartItems(aid) {
  this.lendService.onAddToCard(aid).subscribe();
  this.router.navigate(['/lends/']);
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



}
