import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Annonce } from 'models/DataInterface';
import { AnnonceService, DataService, LendService } from '@app/services';
import { AnnonceComponent } from '@app/components';

@Component({
  selector: 'app-my-ads',
  templateUrl: './my-ads.component.html',
  styleUrls: ['./my-ads.component.css']
})
export class MyAdsComponent implements OnInit {
  @Output() fromParent = new EventEmitter<string>();

  annonces: Annonce[];
  posts: Annonce[];




  constructor(  private http: HttpClient,
    private annonceService: AnnonceService,
    private annonceComponent: AnnonceComponent,
    private lendService: LendService,
    private dataService: DataService,
    private router: Router) {
   }

  ngOnInit(){

    this.dataService.getPosts().subscribe(posts => {
      // if(posts['annonces']['owner'] == this.http.)
      this.posts = posts['annonces'];
      this.dataService.postsData = posts
    });
    console.log(localStorage.getItem('dataSource'));

  }

  callParent(): void {
    this.fromParent.next('something');
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

  removeAnnonce(aid) {
    this.annonceService.removeAnnonce(aid).subscribe();
    // return this.item._id != aid;
    return this.router.navigate(['/myads']);
  }

  updateAds(id, params){
    this.annonceService.updateAds(id, params).subscribe();
    return this.router.navigate(['/myads']);
  }

  createAds(params){
    this.annonceService.createAds(params).subscribe();
    return this.router.navigate(['/myads']);

  }

  getAnnonceItemById(aid){
    this.annonceService.getAnnonceItemById(aid).subscribe();
    this.router.navigate(['/annonces/',aid]);
  }


}
