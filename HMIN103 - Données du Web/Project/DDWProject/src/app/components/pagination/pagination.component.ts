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

    //an example array of 150 items to be paged
    this.items = Array(150).fill(0).map((x, i) => ({ id: (i + 1), name: `Item ${i + 1}`}));

  }

  onChangePage(pageOfItems: Array<any>) {
    // update current page of items
    this.pageOfItems = pageOfItems;

  }




}
