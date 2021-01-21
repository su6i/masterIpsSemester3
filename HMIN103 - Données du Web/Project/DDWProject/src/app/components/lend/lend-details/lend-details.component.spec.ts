import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LendDetailsComponent } from './lend-details.component';

describe('LendDetailsComponent', () => {
  let component: LendDetailsComponent;
  let fixture: ComponentFixture<LendDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LendDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LendDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
