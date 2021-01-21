import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditAnnonceComponent } from './add-edit-annonce.component';

describe('AddEditAnnonceComponent', () => {
  let component: AddEditAnnonceComponent;
  let fixture: ComponentFixture<AddEditAnnonceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditAnnonceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
