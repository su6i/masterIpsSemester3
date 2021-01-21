import { Component, OnInit } from '@angular/core';
// import { Paginate } from 'jw-paginate';

@Component({
  selector: 'app-pagination',
  styleUrls: ['./pagination.component.css'],
  templateUrl: './pagination.component.html'
})
export class PaginationComponent implements OnInit {

  items = [];
  pageOfItems: Array<any>;


  // @Input() items: Array<any>;

  pager: any = {};

  constructor() { }


  ngOnInit() {
      // // set page if items array isn't empty
      // if (this.items && this.items.length) {
      //     this.setPage(this.initialPage);
      // }
  }

  // ngOnChanges(changes: SimpleChanges) {
  //     // reset page if items array has changed
  //     if (changes.items.currentValue !== changes.items.previousValue) {
  //         this.setPage(this.initialPage);
  //     }

    //an example array of 150 items to be paged
    this.items = Array(150).fill(0).map((x, i) => ({ id: (i + 1), name: `Item ${i + 1}`}));



  // setPage(page: number) {
  //     // get new pager object for specified page
  //     this.pager = Paginate(this.items.length, page, this.pageSize, this.maxPages);

  //     // get new page of items from items array
  //     var pageOfItems = this.items.slice(this.pager.startIndex, this.pager.endIndex + 1);

  //     // call change page function in parent component
  //     this.changePage.emit(pageOfItems);
  // }


  onChangePage(pageOfItems: Array<any>) {
    // update current page of items
    this.pageOfItems = pageOfItems;


}

}
