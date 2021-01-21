import { Component, OnInit      } from '@angular/core'                  ;
import { ActivatedRoute, Params, Router } from '@angular/router'                ;
import { AnnonceService         } from '../../services/annonce.service' ;
import { CommonModule           } from "@angular/common"                ;
import { LendService              } from '../../services/lend.service'      ;

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.css']
})
export class TypesComponent implements OnInit {
  selectedAnnonces: Object[];
  type: string;
  categoryFromParentURL:string;


  constructor(private annonceService: AnnonceService,
    private lendService: LendService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.type= params.get('types');
      this.categoryFromParentURL =  this.activatedRoute.snapshot.params['category'];
      this.annonceService.getSelectedAnnonces(this.categoryFromParentURL,this.type).subscribe(selectedAnnonces =>{
        this.selectedAnnonces = selectedAnnonces;
        });
    });
  }

  addCartItems(pid) {
    this.lendService.onAddToCard(pid).subscribe((lend =>{
      this.router.navigate(['/lends/']);
    }));

    }

    getAnnonceItemById(pid: string){
      this.annonceService.getAnnonceItemById(pid);
      this.router.navigate(['/annonces',pid]);
    }
}
