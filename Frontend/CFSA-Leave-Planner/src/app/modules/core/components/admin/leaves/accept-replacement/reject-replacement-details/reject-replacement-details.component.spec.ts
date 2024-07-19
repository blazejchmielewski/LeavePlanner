import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectReplacementDetailsComponent } from './reject-replacement-details.component';

describe('RejectReplacementDetailsComponent', () => {
  let component: RejectReplacementDetailsComponent;
  let fixture: ComponentFixture<RejectReplacementDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RejectReplacementDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RejectReplacementDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
