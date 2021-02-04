import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditDetailsUsersComponent } from './add-edit-details-users.component';

describe('AddEditDetailsUsersComponent', () => {
  let component: AddEditDetailsUsersComponent;
  let fixture: ComponentFixture<AddEditDetailsUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditDetailsUsersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditDetailsUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
