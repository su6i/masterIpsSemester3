import { Component, OnInit              } from '@angular/core'                     ;
import { ProductService                 } from '../../services/product.service'    ;
import { OrderService                   } from '../../services/order.service'      ;
import { HttpClient                     } from '@angular/common/http';
import { ActivatedRoute, Params, Router } from '@angular/router'                ;
import { DataService                    } from '../../services/data.service'         ;
import { Product                        } from '../../../../models/DataInterface'    ;



@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Object[];
  item: object;
  id:string;
  posts: Product[]
  count: number;


  constructor(
    private http: HttpClient,
    private productService: ProductService,
    private orderService: OrderService,
    private dataService: DataService,
    private router: Router) { }

  ngOnInit(){

    this.dataService.getPosts().subscribe(posts => {
      this.posts = posts['products'];
      this.count = posts['count'];
      this.dataService.postsData = posts
    });
  }

  onSelectedOption(e) {
    this.getFilteredExpenseList();
  }

  getFilteredExpenseList() {
    if (this.dataService.searchOption.length > 0)
      {
        this.posts = this.dataService.filteredListOptions();
      }
      else {
        this.posts = this.dataService.postsData;
      }
  }

  addCartItems(pid) {
    this.orderService.onAddToCard(pid).subscribe();
    this.router.navigate(['/orders/']);
    }

    getProductItemById(pid){
      this.productService.getProductItemById(pid).subscribe(product=>{
        this.item = product;
      });
      this.router.navigate(['/products/',pid]);
    }

}
