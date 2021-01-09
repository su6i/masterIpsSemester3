import { Component, OnInit                 } from '@angular/core'                     ;
import { ActivatedRoute, Params, Router    } from '@angular/router'                   ;
import { Location                          } from '@angular/common';
import { ProductService                    } from '../../../services/product.service'    ;
import { OrderService                      } from '../../../services/order.service'      ;
import { HttpClient                        } from '@angular/common/http';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  item:object;


  constructor(
    private productService: ProductService,
    private orderService: OrderService,
    private _location: Location,
    private router: Router,
    private http:HttpClient) { }

  ngOnInit() {
    const pid = window.location.pathname.split('/')[2];
    this.productService.getProductItemById(pid).subscribe(product=>{
      this.item = product;
    });
  }

  addCartItems(pid) {
    this.orderService.onAddToCard(pid).subscribe();
    this.router.navigate(['/orders']);
    }

backClicked() {
  this._location.back();
}




}
