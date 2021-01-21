import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { ActivatedRoute, Params, Router    } from '@angular/router'                     ;
import { Location                          } from '@angular/common'                     ;
import { AnnonceService                    } from '../../../services/annonce.service'      ;
import { LendService                       } from '../../../services/lend.service'      ;
import { HttpClient                        } from '@angular/common/http'                ;
import { Observable } from 'rxjs';
import { Annonce } from 'models/DataInterface';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  item:Annonce;
  // @Input() removeAnnonce: Function;

  constructor(
    private annonceService: AnnonceService,
    private lendService: LendService,
    private _location: Location,
    private router: Router,
    private http:HttpClient) { }

  ngOnInit() {
    const aid = window.location.pathname.split('/')[2];
    this.annonceService.getAnnonceItemById(aid).subscribe(annonce=>{
      this.item = annonce;
    });
  }

  addCartItems(aid) {
    this.lendService.onAddToCard(aid).subscribe();
    this.router.navigate(['/lends']);
    }

backClicked() {
  this._location.back();
}

// callParent() {
//   this.removeAnnonce.emit('removeAnnonce');
// }

removeAnnonce(aid) {
  this.annonceService.removeAnnonce(aid).subscribe();
  // return this.item._id != aid;
  return this.router.navigate(['/annonces']);

}


}
