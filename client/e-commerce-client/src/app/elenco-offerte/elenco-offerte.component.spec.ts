import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElencoOfferteComponent } from './elenco-offerte.component';

describe('ElencoOfferteComponent', () => {
  let component: ElencoOfferteComponent;
  let fixture: ComponentFixture<ElencoOfferteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElencoOfferteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElencoOfferteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
