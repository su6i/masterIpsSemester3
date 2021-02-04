import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AbstractControlOptions, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { HttpClient,HttpParams } from '@angular/common/http';


import { AlertService } from '../../../services/alert.service';
import { MustMatch } from '../../../_helpers/must-match.validator';
import { AnnonceService } from '../../../services/annonce.service';
// import { ValidateService     } from '../../../services/validate.service' ;


@Component({
  selector: 'app-add-edit-details-annonce',
  templateUrl: './add-edit-details-annonce.component.html',
  styleUrls: ['./add-edit-details-annonce.component.css']
})
export class AddEditDetailsAnnonceComponent implements OnInit {

    errSwitch    = false;
    succSwitch   = false;
    submitSwitch = false;
    errMsg       : string;
    succMsg      : string;

    form!: FormGroup;
    aid!: string;
    mode: string;
    loading = false;
    submitted = false;
    annonce: object;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private alertService: AlertService,
        private annonceService: AnnonceService,
        // private validateService: ValidateService,

    ) {}

    async ngOnInit() {
      // this.aid = this.route.snapshot.paramMap.get('aid');
      this.aid =  this.route.snapshot.params['aid'];
      if(!this.aid) this.mode = "add";
      if(this.route.snapshot.url[0].path == "editAds") this.mode = "Update";
      if(this.route.snapshot.url[0].path == "annonces") this.mode = "details";
      // console.log("params::::::", this.route.snapshot.params);
      console.log("aid::::::", this.aid);

      // const aid = window.location.pathname.split('/')[2];
      this.annonceService.getAnnonceItemById(this.aid).subscribe(item=>{
        this.annonce = item;
      });








      const formOptions: AbstractControlOptions = { validators: MustMatch('password', 'confirmPassword') };
      this.form = this.formBuilder.group({
          type: ['', Validators.required],
          category: ['', Validators.required],
          name: ['', Validators.required],
          keywords: ['', Validators.required],
          price: ['', Validators.required],
          description: ['', Validators.required],
      }, formOptions);


      if (this.mode != 'add') {
          this.annonceService.getAnnonceItemById(this.aid)
              .pipe(first())
              .subscribe(x => this.form.patchValue(x));
      }
  }

    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }

    onSubmit() {
        this.submitted = true;

        // reset alerts on submit
        this.alertService.clear();

        // stop here if form is invalid
        if (this.form.invalid) {
            return;
        }

        this.loading = true;
        if (this.mode == 'add') this.createAds();
        else if (this.mode.match("update")) this.detailsAds();
         else if (this.mode.match("details")) this.detailsAds();
        else return "What??";
      }

    private createAds() {
        this.annonceService.createAds(this.form.value)
            .pipe(first())
            .subscribe(() => {
                this.alertService.success('User added', { keepAfterRouteChange: true });
                this.router.navigate(['../'], { relativeTo: this.route });
            })
            .add(() => this.loading = false);
    }

    private updateAds() {
        this.annonceService.updateAds(this.aid, this.form.value)
            .pipe(first())
            .subscribe(() => {
                this.alertService.success('User updated', { keepAfterRouteChange: true });
                this.router.navigate(['../../'], { relativeTo: this.route });
            })
            .add(() => this.loading = true);
    }

    private detailsAds() {
        this.annonceService.detailsAds(this.aid)
            .pipe(first())
            .subscribe(() => {
                this.alertService.success('User updated', { keepAfterRouteChange: true });
                this.router.navigate(['../../'], { relativeTo: this.route });
            })
            .add(() => this.loading = true);
    }



}
