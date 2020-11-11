import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterValidationUsedngIfDirectiveComponent } from './register-validation-usedng-if-directive.component';

describe('RegisterValidationUsedngIfDirectiveComponent', () => {
  let component: RegisterValidationUsedngIfDirectiveComponent;
  let fixture: ComponentFixture<RegisterValidationUsedngIfDirectiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterValidationUsedngIfDirectiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterValidationUsedngIfDirectiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
