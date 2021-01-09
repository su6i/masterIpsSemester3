import { Component, OnInit      } from '@angular/core'                  ;
import { ActivatedRoute, Params, Router } from '@angular/router'                ;
import { ProductService         } from '../../services/product.service' ;
import { CommonModule           } from "@angular/common"                ;
import { OrderService              } from '../../services/order.service'      ;

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.css']
})
export class TypesComponent implements OnInit {
  selectedProducts: Object[];
  type: string;
  categoryFromParentURL:string;


  constructor(private productService: ProductService,
    private orderService: OrderService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      this.type= params.get('types');
      this.categoryFromParentURL =  this.activatedRoute.snapshot.params['category'];
      this.productService.getSelectedProducts(this.categoryFromParentURL,this.type).subscribe(selectedProducts =>{
        this.selectedProducts = selectedProducts;
        });
    });
  }

  addCartItems(pid) {
    this.orderService.onAddToCard(pid).subscribe((order =>{
      this.router.navigate(['/orders/']);
    }));

    }

    getProductItemById(pid: string){
      this.productService.getProductItemById(pid);
      this.router.navigate(['/products',pid]);
    }
}
