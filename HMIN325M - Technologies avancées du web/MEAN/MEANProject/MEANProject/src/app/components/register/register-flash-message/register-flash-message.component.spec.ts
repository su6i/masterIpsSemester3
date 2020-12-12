import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterFlashMessageComponent } from './register-flash-message.component';

describe('RegisterFlashMessageComponent', () => {
  let component: RegisterFlashMessageComponent;
  let fixture: ComponentFixture<RegisterFlashMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterFlashMessageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFlashMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
