import { Component, OnInit } from '@angular/core';
import { HttpClient                        } from '@angular/common/http';
import { ActivatedRoute, Params, Router    } from '@angular/router'                   ;
import { Location                          } from '@angular/common';
import { AnnonceService                    } from '../../../services/annonce.service'    ;
import { LendService                      } from '../../../services/lend.service'      ;

@Component({
  selector: 'app-lend-details',
  templateUrl: './lend-details.component.html',
  styleUrls: ['./lend-details.component.css']
})
export class LendDetailsComponent implements OnInit {

  lends:object;
  lend:object;


  constructor(
    private annonceService: AnnonceService,
    private lendService: LendService,
    private _location: Location,
    private activatedRoute: ActivatedRoute,
    private http:HttpClient,
    private router: Router
    ) { }

  ngOnInit(): void {
    const lid = window.location.pathname.split('/')[2];
    this.annonceService.getCartItemById(lid).subscribe(item=>{
      this.lend = item['lend'];
    });
    console.log(localStorage.getItem('dataSource'));

  }

  backClicked() {
    this._location.back();
  }

  removeCartItems(lid) {
    this.lendService.removeCartItems(lid)
    .subscribe();
    this.router.navigate(['/lends']);
  }

}


