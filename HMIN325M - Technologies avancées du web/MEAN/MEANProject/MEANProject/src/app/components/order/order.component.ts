import { Component, OnInit         } from '@angular/core'                     ;
import { ActivatedRoute, Params, Router    } from '@angular/router'                   ;
import { Observable                } from 'rxjs'                              ;
import { ProductService } from 'src/app/services/product.service';
import { OrderService            } from '../../services/order.service'    ;
import { ProductComponent            } from '../product/product.component'    ;

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  orders: Object[];
  count: number;
  pid: string;



  constructor(
    private orderService: OrderService,
    private productService: ProductService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.orderService.getCartItems().subscribe((orders: any) =>{
      this.orders = orders['orders'];
      //this.count = orders.count;
    });


  }

  addCartItems(pid) {
    this.orderService.onAddToCard(pid);
  }

  removeCartItems(oid) {
    this.orderService.removeCartItems(oid)
    .subscribe((order) => {
      this.orders = this.orders.filter((order:any) => {
        return order._id != oid;
      })
    });
  }

  // Add order information to the product information
  getCartItemById(oid){
    this.productService.getCartItemById(oid).subscribe((order =>{
    }));
    this.router.navigate(['/orders',oid]);
  }


  }


