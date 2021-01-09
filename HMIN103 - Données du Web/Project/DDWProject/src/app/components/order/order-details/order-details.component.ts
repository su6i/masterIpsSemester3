import { Component, OnInit                 } from '@angular/core'                     ;
import { HttpClient                        } from '@angular/common/http';
import { ActivatedRoute, Params, Router    } from '@angular/router'                   ;
import { Location                          } from '@angular/common';
import { ProductService                    } from '../../../services/product.service'    ;
import { OrderService                      } from '../../../services/order.service'      ;

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  orders:object;
  order:object;


  constructor(
    private productService: ProductService,
    private orderService: OrderService,
    private _location: Location,
    private activatedRoute: ActivatedRoute,
    private http:HttpClient,
    private router: Router
    ) { }

  ngOnInit(): void {
    const oid = window.location.pathname.split('/')[2];
    this.productService.getCartItemById(oid).subscribe(item=>{
      this.order = item['order'];
    });

  }

  backClicked() {
    this._location.back();
  }

  removeCartItems(oid) {
    this.orderService.removeCartItems(oid)
    .subscribe();
    this.router.navigate(['/orders']);
  }




}
