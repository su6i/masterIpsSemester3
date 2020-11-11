import { Component, OnInit         } from '@angular/core'                        ;
import { ActivatedRoute, Params, Router    } from '@angular/router'                      ;
import { Observable                } from 'rxjs'                                 ;
import { ProductService            } from '../../../services/product.service'    ;
import { OrderService              } from '../../../services/order.service'      ;

@Component({
  selector: 'app-category-sub',
  templateUrl: './category-sub.component.html',
  styleUrls: ['./category-sub.component.css']
})
export class CategorySubComponent implements OnInit {

  types: Object[];
  category: string;

  constructor(
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private router        : Router,
    private orderService  : OrderService) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.category = params.get('category');
      this.productService.getTypes(this.category).subscribe(types =>{
        this.types = types;
      });
    });
  }

  addCartItems(pid) {
    this.orderService.onAddToCard(pid).subscribe();
    this.router.navigate(['/orders']);
    }

    getCartItemsById(pid: string){
      return this.productService.getProductItemById(pid);
      this.router.navigate(['/products/',pid]);
    }

}
