import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentFormListComponent } from './comment-form-list.component';

describe('CommentFormListComponent', () => {
  let component: CommentFormListComponent;
  let fixture: ComponentFixture<CommentFormListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentFormListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentFormListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
