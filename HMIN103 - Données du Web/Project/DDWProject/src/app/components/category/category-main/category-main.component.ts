import { Component, OnInit         } from '@angular/core'                        ;
import { ActivatedRoute, Params    } from '@angular/router'                      ;
import { Observable                } from 'rxjs'                                 ;
import { AnnonceService            } from '../../../services/annonce.service'    ;

@Component({
  selector: 'app-category-main',
  templateUrl: './category-main.component.html',
  styleUrls: ['./category-main.component.css']
})
export class CategoryMainComponent implements OnInit {

  categories: Object[];

  constructor(private annonceService: AnnonceService) { }

  ngOnInit(){
    this.annonceService.getCategories().subscribe(categories =>{
      this.categories = categories;
    })
  }

}
