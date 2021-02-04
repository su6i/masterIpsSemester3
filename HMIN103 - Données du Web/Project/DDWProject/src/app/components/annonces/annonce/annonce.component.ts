import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '@app/services';
import { Annonce } from 'models/DataInterface';
import { AnnoncesComponent } from '../annonces.component';
import { BsDatepickerConfig             } from 'ngx-bootstrap/datepicker'            ;

@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrls: ['./annonce.component.css']
})
export class AnnonceComponent implements OnInit {

  @Input()  post: Annonce;
  start : Date = new Date(2021,1,23);
  end : Date = new Date(2021,1,31);
  dateRange: Date[];
  days: number;
  datePickerConfig: Partial<BsDatepickerConfig>;


  constructor(
    public annoncesComponent: AnnoncesComponent,
    public authService: AuthService
    ) {
      this.datePickerConfig =Object.assign({},{
        containerClass: 'theme-dark-blue',
        minDate: new Date(2021,1,23),
        maxDate: new Date(2021,2,31),
        dateInputFormat: 'DD/MM/YYYY',
        showTodayButton: true,
        maxDateRange: 10,
        isAnimated: true,

      });

      this.dateRange = [this.start, this.end];
      this.days = this.getDays();


    }

  ngOnInit(): void {
  }

  showDetails: boolean = false;
  toggleDetails(aid): void{
    this.showDetails = !this.showDetails;

  }

  lendDays(date1:any, date2:any) {
    return new Date(date2 - date1).getDate() - 1
  }

  getDays(){
    return  this.lendDays(this.dateRange[0],this.dateRange[1]);
  }


}
