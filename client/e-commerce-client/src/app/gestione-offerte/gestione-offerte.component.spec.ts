import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestioneOfferteComponent } from './gestione-offerte.component';

describe('GestioneOfferteComponent', () => {
  let component: GestioneOfferteComponent;
  let fixture: ComponentFixture<GestioneOfferteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestioneOfferteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestioneOfferteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
