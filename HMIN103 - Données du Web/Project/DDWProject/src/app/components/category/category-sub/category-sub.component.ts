import { Component, OnInit                 } from '@angular/core'                        ;
import { ActivatedRoute, Params, Router    } from '@angular/router'                      ;
import { Observable                        } from 'rxjs'                                 ;
import { AnnonceService                    } from '../../../services/annonce.service'    ;
import { LendService                       } from '../../../services/lend.service'      ;

@Component({
  selector: 'app-category-sub',
  templateUrl: './category-sub.component.html',
  styleUrls: ['./category-sub.component.css']
})
export class CategorySubComponent implements OnInit {

  types: Object[];
  category: string;

  constructor(
    private annonceService: AnnonceService,
    private activatedRoute: ActivatedRoute,
    private router        : Router,
    private lendService   : LendService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.category = params.get('category');
      this.annonceService.getTypes(this.category).subscribe(types =>{
        this.types = types;
      });
    });
  }

  addCartItems(pid) {
    this.lendService.onAddToCard(pid).subscribe();
    this.router.navigate(['/lends']);
    }

    getCartItemsById(aid: string){
      this.annonceService.getAnnonceItemById(aid);
      this.router.navigate(['/annonces/',aid]);
    }

}
