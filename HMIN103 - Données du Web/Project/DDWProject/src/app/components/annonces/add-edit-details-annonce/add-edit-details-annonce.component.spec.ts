import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDetailsAnnonceComponent } from './add-edit-details-annonce.component';

describe('AddEditDetailsAnnonceComponent', () => {
  let component: AddEditDetailsAnnonceComponent;
  let fixture: ComponentFixture<AddEditDetailsAnnonceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDetailsAnnonceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDetailsAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
