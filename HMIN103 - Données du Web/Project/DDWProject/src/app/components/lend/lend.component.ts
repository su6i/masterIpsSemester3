import { Component, OnInit                 } from '@angular/core';
import { ActivatedRoute, Params, Router    } from '@angular/router'                   ;
import { Observable                        } from 'rxjs'                              ;
import { AnnonceService                    } from 'src/app/services/annonce.service'  ;
import { LendService                       } from '../../services/lend.service'       ;

@Component({
  selector: 'app-lend',
  templateUrl: './lend.component.html',
  styleUrls: ['./lend.component.css']
})
export class LendComponent implements OnInit {

  lends: Object[];
  count: number;
  aid: string;



  constructor(
    private lendService: LendService,
    private annonceService: AnnonceService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.lendService.getCartItems().subscribe((lends: any) =>{
      this.lends = lends['lends'];
      this.count = lends.count;
    });


  }

  addCartItems(aid) {
    this.lendService.onAddToCard(aid);
  }

  // Add lend information to the annonce information
  getCartItemById(lid){
    this.annonceService.getCartItemById(lid).subscribe();
    this.router.navigate(['/lends',lid]);
  }


  removeCartItems(lid) {
    this.lendService.removeCartItems(lid)
    .subscribe((lend) => {
      this.lends = this.lends.filter((lend:any) => {
        return lend._id != lid;
      })
    });
  }


}

