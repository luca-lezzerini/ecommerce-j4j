import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ElencoSpedizioniComponent } from './elenco-spedizioni.component';

describe('ElencoSpedizioniComponent', () => {
  let component: ElencoSpedizioniComponent;
  let fixture: ComponentFixture<ElencoSpedizioniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ElencoSpedizioniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ElencoSpedizioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
