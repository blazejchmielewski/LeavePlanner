import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptReplacementDetailsComponent } from './accept-replacement-details.component';

describe('AcceptReplacementDetailsComponent', () => {
  let component: AcceptReplacementDetailsComponent;
  let fixture: ComponentFixture<AcceptReplacementDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptReplacementDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcceptReplacementDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
