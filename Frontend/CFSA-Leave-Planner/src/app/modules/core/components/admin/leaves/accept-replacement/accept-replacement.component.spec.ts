import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptReplacementComponent } from './accept-replacement.component';

describe('AcceptReplacementComponent', () => {
  let component: AcceptReplacementComponent;
  let fixture: ComponentFixture<AcceptReplacementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptReplacementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcceptReplacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
