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
  selector: 'app-add-edit-annonce',
  templateUrl: './add-edit-annonce.component.html',
  styleUrls: ['./add-edit-annonce.component.css']
})
export class AddEditAnnonceComponent implements OnInit {

    errSwitch    = false;
    succSwitch   = false;
    submitSwitch = false;
    errMsg       : string;
    succMsg      : string;

    form!: FormGroup;
    id!: string;
    isAddMode!: boolean;
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
      // this.id = this.route.snapshot.paramMap.get('aid');
      this.id =  this.route.snapshot.params['aid'];
      console.log(this.id);
      this.isAddMode = !this.id;

      const aid = window.location.pathname.split('/')[2];
      this.annonceService.getAnnonceItemById(aid).subscribe(item=>{
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


      if (!this.isAddMode) {
          this.annonceService.getAnnonceItemById(this.id)
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
        if (this.isAddMode) {
            this.createAds();
        } else {
            this.updateAds();
        }
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
        this.annonceService.updateAds(this.id, this.form.value)
            .pipe(first())
            .subscribe(() => {
                this.alertService.success('User updated', { keepAfterRouteChange: true });
                this.router.navigate(['../../'], { relativeTo: this.route });
            })
            .add(() => this.loading = true);
    }



}
